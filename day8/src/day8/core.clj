(ns day8.core
    (:require [clojure.tools.trace :refer [trace]]))
(defn parse-row
  [row]
  (let [[_ v1 fun n1 v2 cmp n2]
            (re-matches #"(\w+)\s(\w+)\s(-?\d+)\sif\s(\w+)\s(.*)\s(-?\d+)" row)
        fun (if (= fun "inc") + -)
        n1  (read-string n1)
        n2  (read-string n2)
        cmp (cond
              (= cmp "==") =
              (= cmp "!=") not=
              (= cmp "<") <
              (= cmp "<=") <=
              (= cmp ">") >
              (= cmp ">=") >=)]
    [fun cmp v1 v2 n1 n2]))

(defn parse-input
  [text]
  (->> text
    clojure.string/split-lines
    (map parse-row)))

(def input (parse-input (slurp "input.txt")))

(defn step-program
  [instructions bank pc]
  (let [[fun cmp var1 var2 n1 n2] (nth instructions pc)
        val1 (or (get bank var1) 0)
        val2 (or (get bank var2) 0)]
    (if (cmp val2 n2)
        (assoc bank var1 (fun val1 n1))
        (assoc bank var1 val1))))

(defn highest
  [bank]
  (->> bank
    vals
    (remove nil?)
    (apply max)))

(def part1
  (let [num-instr (count input)]
    (->> (range 0 num-instr)
       (reduce (partial step-program (vec input)) {})
       vals
       (remove nil?)
       (apply max))))

(def part2
  (let [num-instr (count input)]
    (->> (range 0 num-instr)
       (reductions (partial step-program (vec input)) {})
       rest
       (map highest)
       (apply max))))

(do
   (println part1)
   (println part2))
