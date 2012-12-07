(ns enlight.core
  (import [mikera.image]))

(defn render 
  "Render a scene to a new bufferedimage"
  ([scene
              & {:keys [width height] 
                 :or {width 256 height 256}}]
  (let [b nil]
    b)))