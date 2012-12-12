(ns enlight.samples.demo
  (:require [mikera.vectorz.core :as v])
  (:use clojure.reflect)
  (:use [enlight core]))

(set! *warn-on-reflection* true)
(set! *unchecked-math* true)

(def EXAMPLE-SCENE
  [:camera {:position [0 0 -5]
            :direction [0 0 1]
            :up [0 1 0]
            :right [1 0 0]}
   :root (sphere)
  ])


(defn demo []
  (compile-scene EXAMPLE-SCENE)
  
  (show EXAMPLE-SCENE)
  
)