(ns enlight.test-core
  (:use [enlight core])
  (:import [java.awt.image BufferedImage])
  (:use [clojure test]))



(deftest test-render
  (testing "Basic render"
    (let [^BufferedImage im (render {} :width 20 :height 20)]
      (is (= 20 (.getWidth im))))))
