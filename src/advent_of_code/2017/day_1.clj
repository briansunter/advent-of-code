(ns advent-of-code.2017.day-1
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]
            [clojure.java.io :as io]))

(def group-sequential-digits-xforms
  (comp
   (partition-by identity)
   (filter #(<= 2 (count %)))))

(def group-leading-digits-xforms
  (comp
   group-sequential-digits-xforms
   (map #(drop 1 %))))

(def char->number-xforms (map #(Character/digit % 10)))

(s/def ::digit-string
  (s/with-gen #(number? (read-string %))
    #(gen/fmap str (s/gen pos-int?))))

(defn sum-number-groups-reduce-fn
  ([]  0)
  ([i] i)
  ([r i] (+ r (reduce + i))))

(def sum-leading-digits-xforms
  (comp
   char->number-xforms
   group-leading-digits-xforms))

(s/fdef sum-leading-digits
        :args (s/cat :digits ::digit-string)
        :ret #(<= 0 %))

(defn sum-leading-digits
  [digits]
  (let [circular-digits (str digits (first digits))]
    (transduce group-leading-digits-xforms
               sum-number-groups-reduce-fn
               circular-digits)))

(defn read-puzzle-input
  [filename]
  (some->
   (io/resource "2017/day_1_input.txt")
   io/file
   slurp
   clojure.string/trim))

(defn solve-puzzle
  []
  (->>
   (read-puzzle-input "2017/day_1_input.txt")
   (s/assert ::digit-string)
   (sum-leading-digits)))
