(ns enlight.core
  (require [mikera.vectorz.core :as v])
  (require [enlight.colours :as c])
  (import [mikera.vectorz Vector3 Vector4 AVector Vectorz])
  (import [java.awt.image BufferedImage])
  (import [mikera.image]))

(set! *warn-on-reflection* true)
(set! *unchecked-math* true)

(defmacro error
  "Throws an error with the provided message(s)"
  ([& vals]
    `(throw (java.lang.RuntimeException. (str ~@vals)))))


(defn compile-camera
  "Compiles a camera to ensure necessary vectors are present"
  ([camera]
    (let [camera (or camera {})
          pos (or (:position camera) (v/vec3))
          dir (or (:direction camera) (v/vec3 0 0 1))
          up (or (:up camera) (v/vec3 0 1 0))
          right (or (:right camera) (v/vec3 1 0 0))]
      (merge camera
             {:up (v/vec3 up)
              :right (v/vec3 right)
              :direction (v/vec3 dir)
              :position (v/vec3 pos)}))))

(defn new-image
  "Creates a new blank image"
  ([w h]
    (mikera.gui.ImageUtils/newImage (int w) (int h))))

(defn compile-scene
  "Optimises a scene for rendering"
  ([scene]
    (let [scene (or scene {})
          scene (assoc scene :camera (compile-camera (:camera scene)))]
      scene)))

(defn trace-ray 
  "Raytraces a specific ray from the eye position, returning the colour in an out vector"
  ([scene ^Vector3 pos ^Vector3 direction ^Vector4 colour-out]))

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

(defn render 
  "Render a scene to a new bufferedimage"
  (^BufferedImage [scene
              & {:keys [width height] 
                 :or {width 256 height 256}}]
  (let [width (int width)
        height (int height)
        scene (compile-scene scene)
        camera (:camera scene)
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
          (.setRGB im ix iy (c/rgb-from-vector dir)))))
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

(defn test [] 
  (show {})
)