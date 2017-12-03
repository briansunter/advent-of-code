(ns advent-of-code.2017.day-1-test
  (:require [advent-of-code.2017.day-1 :as sut]
            [clojure.test :refer [deftest testing is]]))

(deftest day-1
  (testing "Sample Input"
    (is (= 3 (sut/sum-next-digits "1122")))
    (is (= 4 (sut/sum-next-digits "1111")))
    (is (= 0 (sut/sum-next-digits "1234")))
    (is (= 9 (sut/sum-next-digits "91212129")))))
