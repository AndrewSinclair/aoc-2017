(ns day3.core
    (:require [clojure.tools.trace :refer [trace]]))

(def input 312051)

(defn round-up-odd
  [x]
  (let [n (int (Math/ceil x))]
    (if (odd? n) n (inc n))))

(defn **2
  [n]
  (* n n))

(defn max-outer-ring
  [n]
  (-> n
    (Math/sqrt)
    (round-up-odd)
    **2))

(defn ring-num
  [n]
  (-> n
    (Math/sqrt)
    (round-up-odd)
    inc
    (quot 2)
    dec))

(defn side-length
  [n]
  (- (* (inc (ring-num n)) 2) 2))

(defn max-num-on-same-side
  [n]
  (->> n
    max-outer-ring
    (iterate (fn [a] (- a (side-length n))))
    (take-while #(<= n %))
    last))

(defn mid-point-val
  [n]
  (->
    (-
     (max-num-on-same-side n)
     (side-length n))
    (+ (quot (side-length n) 2))))

(defn part1
  []
  (+
   (ring-num input)
   (Math/abs (- (mid-point-val input) input))))

(defn part2
  []
  nil)

(do
   (println (part1))
   (println (part2)))
