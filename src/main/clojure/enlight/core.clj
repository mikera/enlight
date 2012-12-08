(ns enlight.core
  (require [mikera.vectorz.core :as v])
  (import [mikera.vectorz Vector3 Vector4 AVector Vectorz])
  (import [java.awt.image BufferedImage])
  (import [mikera.image]))

(set! *warn-on-reflection* true)
(set! *unchecked-math* true)

(defmacro error
  "Throws an error with the provided message(s)"
  ([& vals]
    `(throw (java.lang.RuntimeException. (str ~@vals)))))

(def EXAMPLE-SCENE
  {:camera {:position [0 0 -5]
            :direction [0 0 1]
            :up [0 1 0]
            :right [1 0 0]}})


(defn vec3
  "Coerces to a Vector3"
  (^Vector3 [a]
    (let [^AVector v (v/to-vector a)]
      (if (instance? Vector3 v)
        v
        (error "Can't convert to vector3: " (str a))))))

(defn compile-camera
  "Compiles a camera to ensure necessary vectors are present"
  ([camera]
    (let [camera (or camera {})
          pos (or (:position camera) (Vector3.))
          dir (or (:direction camera) (v/of 0 0 1))
          up (or (:up camera) (v/of 0 1 0))
          right (or (:right camera) (v/of 1 0 0))]
      (merge camera
             {:up (vec3 up)
              :right (vec3 right)
              :direction (vec3 dir)
              :position (vec3 pos)}))))

(defn new-image
  "Creates a new blank image"
  ([w h]
    (mikera.gui.ImageUtils/newImage (int w) (int h))))

(defn compile-scene
  "Optimises a scene for rendering"
  ([scene]
    (let [scene (or scene EXAMPLE-SCENE)
          scene (assoc scene :scene (compile-camera (:camera scene)))]
      scene)))

(defn trace-ray 
  "Raytraces a specific ray from the eye position, returning the colour in an out vector"
  ([scene ^Vector3 pos ^Vector3 direction ^Vector4 colour-out]))

(defn position 
  (^Vector3 [camera]
    (or (:position camera) (Vector3.))))

(defn direction 
  (^Vector3 [camera]
    (:direction camera)))

(defn up-direction 
  (^Vector3 [camera]
    (:up camera)))

(defn right-direction 
  (^Vector3 [camera]
    (:right camera)))

(defn render 
  "Render a scene to a new bufferedimage"
  (^BufferedImage [scene
              & {:keys [width height] 
                 :or {width 256 height 256}}]
  (let [width (int width)
        height (int height)
        scene (compile-scene scene)
        camera (:camera scene)
        colour-result (Vector4.)
        ^Vector3 camera-pos (position camera)
        ^Vector3 camera-up (up-direction camera)
        ^Vector3 camera-right (right-direction camera)
        ^Vector3 direction (direction camera)
        ^BufferedImage im (new-image width height)]
    (dotimes [ix width]
      (dotimes [iy height]
        (let []
          (.setRGB im ix iy (mikera.image.Colours/randomARGBColour)))))
    im)))

(defn display
  "Displays an image in a new frame"
  [^BufferedImage image
   & {:keys [title]}]
  (mikera.gui.Frames/displayImage image (str (or title "Enlight Render"))))

(defn show 
  "Renders and displays a scene in a new Frame"
  ([x
    & {:keys [width height title] 
       :or {width 256 height 256}
       :as params}]
  (cond
    (instance? BufferedImage x)
      (display x :title title)
    :else 
      (display (apply render x (apply concat params)) :title title))))