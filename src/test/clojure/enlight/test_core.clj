(ns enlight.test-core
  (:use [enlight core])
  (:require [enlight.samples.demo :as d])
  (:import [java.awt.image BufferedImage])
  (:use [clojure test]))

(deftest test-camera
  (testing "creating"
    (let [camera (compile-camera nil)]
      (is (not= camera nil))))
  (testing "compiling graph with cameras"
    (let [graph (compile-scene-list [:camera :camera])]
      (is (graph :camera))))) 

(deftest expected-scene-errors
  (testing "compiling graph with no camera"
    (is (thrown? Throwable (render [])))))

(deftest test-render
  (testing "Basic render"
    (let [^BufferedImage im (render d/EXAMPLE-SCENE :width 20 :height 20)]
      (is (= 20 (.getWidth im))))))

(deftest test-scene-desc
  (let [c (compile-all [:sphere])]
    (is (scene-object? c))
    (is (= :sphere (:type c)))))