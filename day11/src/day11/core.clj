(ns day11.core
    (:require [clojure.tools.trace :refer [trace]]))

(defn dir->fn
  [dir]
  (case dir
    "n"  (fn [[x y]] [x (inc y)])
    "s"  (fn [[x y]] [x (dec y)])
    "nw" (fn [[x y]] [(dec x) (inc y)])
    "ne" (fn [[x y]] [(inc x) y])
    "sw" (fn [[x y]] [(dec x) y])
    "se" (fn [[x y]] [(inc x) (dec y)])))

(defn parse-input
  [text]
  (->>
    (clojure.string/split text #",")
    (map dir->fn)))

(defn dist
  [[x y]]
  (let [x' (Math/abs x)
        y' (Math/abs y)]
    (if (or (and (pos? x) (pos? y))
            (and (neg? x) (neg? y)))
      (+ x' y')
      (max x' y'))))

(def input (parse-input (slurp "input.txt")))

(def part1
  (->> input
    (reduce (fn [acc f] (f acc)) [0 0])
    dist))

(def part2
  (->> input
    (reductions (fn [acc f] (f acc)) [0 0])
    (map dist)
    (apply max)))

(do
   (println part1)
   (println part2))
