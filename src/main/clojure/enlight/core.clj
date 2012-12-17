(ns enlight.core
  (:require [mikera.vectorz.core :as v])
  (:require [mikera.vectorz.matrix :as m])
  (:require [enlight.colours :as c])
  (:require [clisk.core :as clisk])
  (:refer-clojure :exclude [compile])
  (:import [mikera.vectorz Vector3 Vector4 AVector Vectorz])
  (:import [mikera.transformz ATransform])
  (:import [enlight.model ASceneObject IntersectionInfo])
  (:import [mikera.vectorz.geom Ray BoundBox])
  (:import [enlight.model.primitive Sphere SkySphere Union])
  (:import [java.awt.image BufferedImage])
  (:import [mikera.image]))

(set! *warn-on-reflection* true)
(set! *unchecked-math* true)

(def ^:dynamic *show-warnings* false)

(declare compile)
(declare compile-object)
(declare compile-function)
(declare with)

(defmacro error
  "Throws an error with the provided message(s)"
  ([& vals]
    `(throw (enlight.EnlightError. (str ~@vals)))))

(defmacro warn
  "Logs a warning as long as *show-warnings* is bound to true"
  ([& vals]
    `(if *show-warnings*
       (binding [*out* *err*]
         (println (str "WARNING: " ~@vals))))))


(defmacro function
  "Produces a VectorFunction for a given clisk node" 
  ([node]
    `(clisk/vector-function ~node :input-dimensions 3)))

;; ===========================================================
;; primitive constructors

(defn sphere 
  "Creates a sphere"
 (^ASceneObject []
    (sphere (v/vec3) 1.0))
  (^ASceneObject [centre radius]
    (Sphere. (v/vec centre) 1.0)))

(defn sky-sphere 
  (^ASceneObject []
    (SkySphere.))
  (^ASceneObject [texture]
    (with (SkySphere.) {:colour (compile-function texture)})))

(defn with 
  "Modifies a scene object with a map of new/updated property values. Properties not valid for the given object are ignored"
  ([object props]
    (if (instance? ASceneObject object)
      (.with ^ASceneObject object props)
      (merge object props))))

(defn union
  "Creates a union of multiple objects"
  (^ASceneObject [& objects]
    (Union/of ^java.util.List (vec (map compile-object objects)))))


;; ===========================================================
;; scene compilation

(defn to-transform 
  ([x]
    (cond 
      (instance? ATransform x) x
      (instance? AVector x) (m/constant-transform x)
      :default (error "Can't convert to a transform: " x))))

(def param-conversions
  {:colour #(to-transform %)})

(defn convert-param [key val]
  (if-let [conv (param-conversions key)]
    (conv val)
    val))

(defn modify-object 
  [object mods defaults]
  (if-let [[v & vs :as ms] (seq mods)]
    (cond 
      (keyword? v) (recur (with object {v (first vs)}) (next vs) nil)
      (associative? v) (recur (with object v) vs nil)
      (seq defaults) (recur (with object {(first defaults) v}) vs (next defaults))
      :default (error "Can't modify object with " ms))
    object))  ;; no change

(defn compile-function 
  "Compiles a clisk vector function"
  ([obj & more-args]
    (cond 
      (instance? ATransform obj) obj  ;; function already compiled, nice and easy
      (v/vec? obj) (m/constant-transform obj) ;; constant vector
      (list? obj) (clisk/vector-function obj :dimensions (or 3 3)) ;; compile a clisk function? 
      :default (error "not implemented!"))))

(defn object-builder
  [generator default-params]
  (fn [& modifiers]
    (modify-object (generator) modifiers default-params)))

(def object-type-functions
  {:union union
   :function compile-function
   :sphere (object-builder sphere [:centre :radius])})

(defn compile-object-vector
  "Compiles an object vector to produce a scene object"
  ([[type & stuff]]
    (if-let [fun (object-type-functions type)]
      (apply fun (map compile stuff))
      (error "can't find object type function for " type))))

(defn compile-object 
  "Compiles a scene object, presumably in a vector"
  ([obj]
    (cond 
      (instance? ASceneObject obj) obj
      (clojure.core/vector? obj) (compile-object-vector obj)
      :default (error "not implemented!"))))

(defn compile-camera
  "Compiles a camera to ensure necessary vectors are present"
  ([args]
    (let [camera (or args {})
          up (or (:up camera) (v/vec3 0 1 0))
          right (or (:right camera) (v/vec3 1 0 0))
          pos (or (:position camera) (warn "Camera has no position!") (v/vec3))
          dir (or (:direction camera) (warn "Camera has no direction!") (v/vec3 0 0 1))]
      (merge camera
             {:up (v/vec3 up)
              :right (v/vec3 right)
              :direction (v/vec3 dir)
              :position (v/vec3 pos)}))))

(defn compile-vector 
  "Compiles a vector, could be either a scene object or a vectorz vector"
  ([v]
    (if-let [xs (seq v)]
      (let [[k & ks] xs]
        (cond
          (keyword? k) (compile-object v)
          :default (v/vec v)))
      (v/vec v))))

(defn compile 
  "Compiles an element in a scene description"
  ([obj]
    (cond 
      (or (v/vec? obj) 
          (instance? ATransform obj)
          (clojure.core/number? obj)
          (instance? ASceneObject obj)) obj ;; no change, already compiled
      (clojure.core/vector? obj) (compile-vector obj)
      :default (error "Unable to compile: " obj))))


(def ENLIGHT-KEYWORDS
  "List of valid enlight keywords in scene description"
  #{:camera :tag :root})

(defn enlight-keyword? [x]
  (ENLIGHT-KEYWORDS x))

(defn compile-scene-element 
  "Compiles a single element of a scene graph with the provided args (may be nil)"
  ([key args]
    (or (enlight-keyword? key) (error "Not a valid enlight keyword! [" key "]"))
    (case key
      :camera (compile-camera args)
      :root (compile args)
      :tag args
      (error "Enlight keyword not implemented! [" key "]"))))

(defn update-graph [graph key arg]
  "Updates a graph with a given key and argument. arg may be nil."
  (merge graph {key (compile-scene-element key arg)}))

(defn compile-scene-list
  "Compiles a scene list for rendering into a scene graph"
  ([scene]
    (let [empty-graph {}] 
      (compile-scene-list empty-graph scene)))
  ([graph s]
    (if-let [s (seq s)]
      (compile-scene-list graph (first s) (next s))
      graph))
  ([graph key xs]
    (if-let [s (seq xs)]
      (let [next-item (first s)]
        (if (enlight-keyword? next-item)
          (compile-scene-list (update-graph graph key nil) next-item (next s))
          (compile-scene-list graph key (first s) (next s))))
      (update-graph graph key nil)))
  ([graph key arg xs]
    (compile-scene-list (update-graph graph key arg) xs)))


(defn compile-scene [scene]
  "Compiles a scene for rendering, applying any default behavious and validation"
  (compile-scene-list scene))

(defn position 
  (^Vector3 [camera]
    (or (:position camera) (v/vec3))))

(defn direction 
  (^Vector3 [camera]
    (:direction camera)))

(defn up-direction 
  (^Vector3 [camera]
    (:up camera)))

(defn right-direction 
  (^Vector3 [camera]
    (:right camera)))

(defn ray 
  "Creates a new ray with specified origin and direction. Direction must be normalised, or bad things will happen."
  (^Ray [^Vector3 origin ^Vector3 direction]
    (Ray. origin direction)))

;; ======================================================
;; Raytracer core 

(defn trace-ray
  ([^ASceneObject scene-object ^Ray ray ^Vector4 colour-result]
    (let [result (IntersectionInfo.)]
      (trace-ray scene-object ray colour-result result)))
  ([^ASceneObject scene-object ^Ray ray ^Vector4 colour-result ^IntersectionInfo result]
    (if (.getIntersection scene-object ray result)
      (let [hit-object (.intersectionObject result)
            temp (v/vec3) ;; allocation! kill!
            pos (.intersectionPoint result)] 
        (.getPigment hit-object pos temp)
        (.copyTo temp colour-result 0)
        (.set colour-result 3 1.0)  ;; clear transparency
      )
      (.copyTo c/BLACK colour-result 0))))


(defn new-image
  "Creates a new blank image"
  ([w h]
    (mikera.gui.ImageUtils/newImage (int w) (int h))))

(defn render 
  "Render a scene to a new bufferedimage"
  (^BufferedImage [scene
              & {:keys [width height] 
                 :or {width 256 height 256}}]
  (let [width (int width)
        height (int height)
        graph (compile-scene scene)
        ^ASceneObject root (or (:root graph) (error "Scene has no root object!"))
        camera (or (:camera graph) (error "Scene has no camera!"))
        colour-result (v/vec4 [0.5 0 0.8 1])
        ^Vector3 camera-pos (position camera)
        ^Vector3 camera-up (up-direction camera)
        ^Vector3 camera-right (right-direction camera)
        ^Vector3 camera-direction (direction camera)
        ^Vector3 dir (v/vec3)
        ^BufferedImage im (new-image width height)]
    (dotimes [ix width]
      (dotimes [iy height]
        (let [xp (- (/ (double ix) width) 0.5)
              yp (- (/ (double iy) height) 0.5)]
          (.set dir camera-direction)
          (v/add-multiple! dir camera-right xp)
          (v/add-multiple! dir camera-up (- yp))
          (v/normalise! dir)
          (trace-ray (:root graph) (ray camera-pos dir) colour-result)
          (.setRGB im ix iy (c/argb-from-vector4 colour-result)))))
    im)))

(defn display
  "Displays an image in a new frame"
  [^BufferedImage image
   & {:keys [title]}]
  (mikera.gui.Frames/displayImage image (str (or title "Enlight Render"))))

(defn show 
  "Renders and displays a scene in a new Frame"
  ([scene
    & {:keys [width height title] 
       :or {width 256 height 256}
       :as params}]
    (display (apply render scene (apply concat params)) :title title)))
