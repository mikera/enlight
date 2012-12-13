(ns enlight.samples.demo
  (:require [mikera.vectorz.core :as v])
  (:require [clisk core patterns functions])
  (:use clojure.reflect)
  (:use [enlight core]))

(set! *warn-on-reflection* true)
(set! *unchecked-math* true)

(def EXAMPLE-SCENE
  [:camera {:position [0 0 -5]
            :direction [0 0 1]
            :up [0 1 0]
            :right [1 0 0]}
   :root (with (sphere) {:colour (function (clisk.functions/take-components 3 (clisk.patterns/vsnoise)))})
  ])


(defn demo []
  (compile-scene EXAMPLE-SCENE)
  
  (show EXAMPLE-SCENE)
  
)