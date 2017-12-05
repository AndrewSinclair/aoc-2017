(ns day1.core)

(defn input->ints
  [input]
  (->> input
    (map read-string))

(defn pair?
  [[a b]]
  (= a b))

(def puzzle-input (->> (slurp "input.txt") input->ints))

(defn wrap-around
  [xs]
  (conj xs (first xs)))

(defn part1
  []
  (->> puzzle-input
    wrap-around
    (partition-by identity)
    (filter pair?)
    (map first)
    (reduce +)))

(defn part2
  []
  (let [half (quot (count puzzle-input) 2)]
    (->> puzzle-input
      (split-at half)
      (apply map vector)
      (filter pair?)
      flatten
      (reduce +))))

(do
   (println (part1))
   (println (part2)))
