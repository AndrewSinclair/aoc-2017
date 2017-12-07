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

(def initial-rings
  [[1]
   [1 2 4 5 10 11 23 25]])

(defn num-sides
  [n]
  (let [length (side-length n)]
    [(dec length)
     length
     length
     (inc length)]))

(defn not'
  [fun]
  (fn [n] (not (fun n))))


(defn index-ring-start
  [ring-n]
  (->> ring-n
    (* 2)
    dec
    **2))

(defn is-done?
  [[n]]
  true)

(defn step-rings
  [rings]
  (let [ring-n (count rings)]
    nil))


(defn part2
  "And now for something completely different."
  []
  (->> initial-rings
       #_(iterate step-rings)
       #_(drop-while (not' is-done?))))

(do
   (println (part1))
   (println (part2)))
