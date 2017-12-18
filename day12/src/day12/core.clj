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

(def part1
  (->> input
    connected-components
    (filter #(contains-node? "0" %))
    first
    count))

(def part2
  (->> input
    connected-components
    count))

(do
   (println part1)
   (println part2))

