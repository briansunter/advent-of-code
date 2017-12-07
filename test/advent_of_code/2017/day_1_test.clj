(ns advent-of-code.2017.day-1-test
  (:require [advent-of-code.2017.day-1 :as sut]
            [clojure.test.check :as tc]
            [clojure.spec.test.alpha :as st]
            [clojure.pprint :as pprint]
            [clojure.test :refer [deftest testing is]]))

;; Utility functions to intergrate clojure.spec.test/check with clojure.test
(defn summarize-results' [spec-check]
  (map (comp #(pprint/write % :stream nil) st/abbrev-result) spec-check))

(defn check' [spec-check]
  (is (nil? (-> spec-check first :failure)) (summarize-results' spec-check)))

(deftest day-1
  (testing "Sample Input"
    (is (= 3 (sut/sum-next-digits "1122")))
    (is (= 4 (sut/sum-next-digits "1111")))
    (is (= 0 (sut/sum-next-digits "1234")))
    (is (= 9 (sut/sum-next-digits "91212129"))))
  (testing "Generated Input"
    (check' (st/check `sut/sum-next-digits))))
