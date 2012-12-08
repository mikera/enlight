(ns enlight.colours
  (import [mikera.vectorz Vector3 Vector4 AVector Vectorz]))

;; utility functions for treating vectors as colours

(defn rgb-from-vector 
  "Converts a colour vector into an ARGB colour value"
  (^long [^AVector colour]
    (mikera.image.Colours/getRGBClamped (.get colour 0) (.get colour 1) (.get colour 2))))

(defn argb-from-vector4 
  "Converts a colour vector into an ARGB colour value"
  (^long [^Vector4 colour]
    (mikera.image.Colours/getARGBClamped (.x colour) (.y colour) (.z colour) (.t colour))))