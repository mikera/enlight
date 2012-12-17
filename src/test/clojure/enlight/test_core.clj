(ns enlight.test-core
  (:use [enlight core])
  (:require [enlight.samples.demo :as d])
  (:require [mikera.vectorz.core :as v])
  (:require [mikera.vectorz.matrix :as m])
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

(deftest test-literal-compile
  (let [c (compile-all 1)]
    (is (number? c))
    (is (= 1 c))))

(deftest test-vector-compile
  (let [c (compile-all [1 2 3])]
    (is (v/vec? c))
    (is (= 3 (v/length c))))
  (let [c (compile-all [1])]
    (is (v/vec? c))
    (is (= 1 (v/length c)))))

(deftest test-plane-compile
  (let [c (compile-all [:plane])]
    (is (scene-object? c))
    (is (= :plane (:type c)))
    (is (= 0.0 (:distance c))))
  (let [c (compile-all [:plane [1 1 1] 1])]
    (is (scene-object? c))
    (is (v/approx= (v/normalise (v/vec [1 1 1])) (:normal c))))
  (let [c (compile-all [:plane :colour [1 0 0]])]
    (is (scene-object? c))
    (is (= (v/vec [1 0 0]) (m/* (:colour c) (v/vec [10 11 12]))))))

(deftest test-sphere-compile
  (let [c (compile-all [:sphere])]
    (is (scene-object? c))
    (is (= :sphere (:type c)))
    (is (= 1.0 (:radius c))))
  (let [c (compile-all [:sphere [1 1 1] 2])]
    (is (scene-object? c))
    (is (= :sphere (:type c)))
    (is (= 2.0 (:radius c)))
    (is (= (v/vec [1 1 1]) (:centre c))))
  (let [c (compile-all [:sphere :radius 2 :colour [1 0 0]])]
    (is (scene-object? c))
    (is (= :sphere (:type c)))
    (is (= 2.0 (:radius c)))
    (is (= (v/vec [1 0 0]) (m/* (:colour c) (v/vec [10 11 12]))))))

(deftest test-sky-sphere-compile
  (let [c (compile-all [:sky-sphere])]
    (is (scene-object? c))
    (is (= :sky-sphere (:type c)))))

(deftest test-union-compile
  (let [c (compile-all [:union])]
    (is (scene-object? c))
    (is (= :union (:type c)))))