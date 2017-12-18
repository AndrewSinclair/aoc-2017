(ns day12.core
  (:require [loom.graph :refer :all]
            [loom.alg :refer :all]))

(defn parse-row
  [row]
  (let [[id ids] (clojure.string/split row #" <-> ")
        children (clojure.string/split ids #", ")]
    {id children}))

(defn parse-input
  [text]
  (->> (clojure.string/split-lines text)
    (map parse-row)))

(def input
  (->>
    "input.txt"
    slurp
    parse-input
    (apply graph)))

(defn contains-node?
  [id nodes]
  (->> nodes
    (some #{id})))

(defn part1
  [input]
  (->> input
    connected-components
    (filter #(contains-node? "0" %))
    first
    count))

(defn part2
  [input]
  (->> input
    connected-components
    count))

(do
   (println (part1 input))
   (println (part2 input)))
