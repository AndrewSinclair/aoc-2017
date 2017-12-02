(ns day2.core)

(defn parse-input
  [text]
  (->>
    (clojure.string/split-lines text)
    (map #(clojure.string/split % #"\t"))
    (map (fn [v] (map #(Integer. %) v)))))

(def input
  (-> "input.txt"
    slurp
    parse-input))

(defn greatest-diff
  [row]
  (- (apply max row) (apply min row)))

(defn find-evenly-divisble
  [row]
  (first
    (for [i row
          j row
          :when (and (> i j)
                     (zero? (mod i j)))]
         (quot i j))))

(defn part1
  []
  (->> input
    (map greatest-diff)
    (reduce +)))

(defn part2
  []
  (->> input
    (map find-evenly-divisble)
    (reduce +)))

(do
   (println (part1))
   (println (part2)))

