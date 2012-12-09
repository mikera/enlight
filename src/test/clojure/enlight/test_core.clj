(ns enlight.test-core
  (:use [enlight core])
  (:require [enlight.samples demo :as d])
  (:import [java.awt.image BufferedImage])
  (:use [clojure test]))

(deftest test-camera
  (testing "creating"
     (let [camera (compile-camera nil)]
       (is (not= camera nil))))) 

(deftest test-render
  (testing "Basic render"
    (let [^BufferedImage im (render d/EXAMPLE-SCENE :width 20 :height 20)]
      (is (= 20 (.getWidth im))))))
