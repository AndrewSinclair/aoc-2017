(ns day10.core
    (:require [clojure.tools.trace :refer [trace]]))

(defn parse-input
  [text]
  (-> text
       (clojure.string/split #",")
       (->> (map read-string))))

(def input (parse-input (slurp "input.txt")))

(defn shift
  [n xs]
  (->> xs
    cycle
    (drop n)
    (take (count xs))))

(defn reverse-sub
  [from to xs]
  (let [part1 (take from xs)
        part2 (reverse (take to (drop from xs)))
        part3 (drop (+ from to) xs)]
    (concat part1 part2 part3)))

(defn twist
  [rope curr offset]
  (->> rope
     cycle
     (reverse-sub curr offset)
     (drop (- (count rope) offset curr))
     (take (count rope))
     (shift offset)))

(def part1
  nil)

(def part2
  nil)

(do
   (println part1)
   (println part2))
