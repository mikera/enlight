(ns enlight.samples.demo
  (:use [enlight core]))

(set! *warn-on-reflection* true)
(set! *unchecked-math* true)

(def EXAMPLE-SCENE
  [:camera {:position [0 0 -5]
            :direction [0 0 1]
            :up [0 1 0]
            :right [1 0 0]}
  ])


(defn demo []
  (show EXAMPLE-SCENE)
  
)