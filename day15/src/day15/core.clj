(ns day15.core
    (:require [clojure.tools.trace :refer [trace]]))

(def start-a 289)
(def start-b 629)

(def factor-a 16807)
(def factor-b 48271)

(defn next-num
  [prev factor]
  (rem (*' prev factor) Integer/MAX_VALUE))

(defn next-pair
  [[a b]]
  (vector
    (next-num a factor-a)
    (next-num b factor-b)))

(defn get-lower-bits
  [n]
  (bit-and 0x0000ffff n))

(defn judge-pair
  [[a b]]
  (let [lower-a (get-lower-bits a)
        lower-b (get-lower-bits b)]
    (= lower-a lower-b)))

(def part1
  (->> (iterate next-pair [start-a start-b])
    (take 40000000)
    (filter judge-pair)
    count))


(def part2
  (let [next-num-a  #(next-num % factor-a)
        next-num-b  #(next-num % factor-b)
        seq-a (->> (iterate next-num-a start-a) (filter #(zero? (mod % 4))))
        seq-b (->> (iterate next-num-b start-b) (filter #(zero? (mod % 8))))]
    (->>
      (map vector seq-a seq-b)
      (take 5000000)
      (filter judge-pair)
      count)))

(do
   (println part1)
   (println part2))
