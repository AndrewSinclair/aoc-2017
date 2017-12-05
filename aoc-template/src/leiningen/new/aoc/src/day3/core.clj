(ns {{ns-name}}.core
    (:require [clojure.tools.trace :refer [trace]]))

(defn parse-input
  [text]
  ;(clojure.string/split-lines text)
  ;(clojure.string/split text #"\s")
  ;(read-string)
  ;(re-matches re s)
  )

(def input (parse-input (slurp "input.txt")))

(defn part1
  []
  nil)

(defn part2
  []
  nil)

(do
   (println (part1))
   (println (part2)))
