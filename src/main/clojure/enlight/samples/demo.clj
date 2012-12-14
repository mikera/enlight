(ns enlight.samples.demo
  (:require [mikera.vectorz.core :as v])
  (:require [clisk core patterns functions colours])
  (:use clojure.reflect)
  (:use [enlight core]))

(set! *warn-on-reflection* true)
(set! *unchecked-math* true)

(def EXAMPLE-SCENE
  [:camera {:position [0 0 -3]
            :direction [0 0 1]
            :up [0 1 0]
            :right [1 0 0]}
   :root [:union 
            (with (sphere) {:colour (function (clisk.functions/take-components 3 (clisk.patterns/vsnoise)))})
            (with (sphere) {:centre (v/vec3 [-1 0 0])})
            (with (sky-sphere) {:colour (function [0.3 0.4 0.5])})] 
  ])


(defn demo []
  (compile-scene EXAMPLE-SCENE)
  
  (show EXAMPLE-SCENE)
  
)