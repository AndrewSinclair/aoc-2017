(ns day7.core
    (:require [clojure.tools.trace :refer [trace]]))

(defrecord Node [text weight children])
(defrecord Disk [text weight])

(defn parse-left
  [text]
  (let [[disk-name weight] (clojure.string/split text #" ")
        weight (read-string (apply str (butlast (rest weight))))]
    (->Disk disk-name weight)))

(defn parse-right
  [text]
  (when (not (nil? text))
      (clojure.string/split text #", ")))

(defn parse-input
  [text]
  (->> text
    clojure.string/split-lines
    (map #(clojure.string/split % #" -> "))
    (map (fn [[left right]]
           (let [disk (parse-left left)
                 children (parse-right right)]
             (->Node (:text disk) (:weight disk) children))))))

(def input (parse-input (slurp "input.txt")))

(def disk-directory
  (->> input
    (reduce (fn [acc disk]
              (assoc acc (:text disk) disk))
            {})))

(defn get-children
  [parent]
  (->> (:children parent)
    (map #(get disk-directory %))))

(def part1
  (apply disj (set (map :text input))
              (mapcat :children input)))

(defn disk-weight
  [disk]
  (let [children (get-children disk)]
    (+ (:weight disk)
       (reduce + (map disk-weight children)))))

(defn black-sheep
  [args]
  (let [[num1 num2] (keys args)
        [count1 count2] (map #(get args %) [num1 num2])]
    (if (< count1 count2)
      [num1 (- num2 num1)]
      [num2 (- num1 num2)])))

(def part2
  (let [[bad-weight offset] (->> input
                              (map #(map disk-weight (get-children %)))
                              (filter #(not (or (zero? (count %)) (apply = %))))
                              (map frequencies)
                              (map black-sheep)
                              (apply min (map first)))]
    (->> input (filter #(= (disk-weight %) bad-weight))
         ;first :weight (+ offset))))
         )))

(do
   (println part1)
   (println part2))
