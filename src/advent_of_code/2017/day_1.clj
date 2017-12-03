(ns advent-of-code.2017.day-1)

(defn sum-next-digits
  [digits]
  (let [digit-list (into [] (map #(Character/digit % 10) digits))
        circular-digit-list  (conj digit-list (first digit-list))]
    (->>
     circular-digit-list
     (partition-by identity)
     (filter #(<= 2 (count %)))
     (map #(drop 1 %))
     (map (partial reduce +))
     (reduce +))))
