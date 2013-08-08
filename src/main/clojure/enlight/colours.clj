(ns enlight.colours
  (:require [mikera.vectorz.core :as v])
  (:import [mikera.vectorz Vector3 Vector4 AVector Vectorz]))

;; utility functions for treating vectors as colours

(def ^Vector4 TRANSPARENT (v/vec4 [0 0 0 0]))

(def ^Vector4 BLACK (v/vec4 [0 0 0 1]))

(def ^Vector4 WHITE (v/vec4 [1 1 1 1]))

(def ^Vector4 SEMI-TRANSPARENT-GREY (v/vec4 [0.5 0.5 0.5 0.5]))

(defn rgb-from-vector 
  "Converts a colour vector into an ARGB colour value"
  (^long [^AVector colour]
    (mikera.image.Colours/getRGBClamped (.get colour 0) (.get colour 1) (.get colour 2))))

(defn rgb-from-vector3 
  "Converts a colour vector into an ARGB colour value"
  (^long [^Vector3 colour]
    (mikera.image.Colours/getRGBClamped (.x colour) (.y colour) (.z colour))))

(defn argb-from-vector4 
  "Converts a colour vector into an ARGB colour value"
  (^long [^Vector4 colour]
    (mikera.image.Colours/getARGBClamped (.x colour) (.y colour) (.z colour) (.t colour))))