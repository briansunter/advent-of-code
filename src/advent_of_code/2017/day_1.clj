(ns advent-of-code.2017.day-1)

(def group-next-digits-xforms
  (comp
   (partition-by identity)
   (filter #(<= 2 (count %)))
   (map #(drop 1 %))))

(defn sum-next-digits
  [digits]
  (let [circular-digits (str digits (first digits))]
    (transduce (comp
               (map #(Character/digit % 10))
               group-next-digits-xforms
               (map (partial reduce +)))
              +
              circular-digits)))
