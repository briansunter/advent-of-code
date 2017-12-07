(ns advent-of-code.2017.day-1
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]
            [clojure.java.io :as io]))

(def group-next-digits-xforms
  (comp
   (partition-by identity)
   (filter #(<= 2 (count %)))
   (map #(drop 1 %))))

(s/def ::digit-string
  (s/with-gen #(number? (read-string %))
    #(gen/fmap str (s/gen pos-int?))))

(s/fdef sum-next-digits
        :args (s/cat :digits ::digit-string)
        :ret #(<= 0 %))

(defn sum-next-digits
  [digits]
  (let [circular-digits (str digits (first digits))]
    (transduce (comp
                (map #(Character/digit % 10))
                group-next-digits-xforms
                (map (partial reduce +)))
               +
               circular-digits)))

(defn read-puzzle-input
  [filename]
  (some->>
   (io/resource "2017/day_1_input.txt")
   (io/file)
   slurp
   clojure.string/trim))

(defn solve-puzzle
  []
  (sum-next-digits (read-puzzle-input "2017/day_1_input.txt")))
