(ns day6.core)

(defn parse-input
  [text]
  (->>
    (clojure.string/split text #"\s+")
    (map read-string)))

(def input (parse-input (slurp "input.txt")))

(defn find-biggest-block
  [memory]
  (let [biggest (apply max memory)]
    (->> memory
      (take-while #(not= biggest %))
      count)))

(defn redistribute-memory
  [memory]
  (let [next-block    (find-biggest-block memory)
        block-size    (nth memory next-block)
        initial-0s    (repeat (inc next-block) 0)
        ones          (repeat block-size 1)
        additional-0s (repeat 0)
        length        (count memory)
        num-loops     (+ (quot block-size length) 2)
        split-up      (take num-loops (partition length (concat initial-0s ones additional-0s)))
        sums          (apply map + split-up)]
    (->> (assoc (vec memory) next-block 0)
      (map + sums))))

(defn collect-until-duplicate
  [acc memory]
  (if (acc memory)
    (reduced acc)
    (conj acc memory)))

(defn collect-until-duplicate'
  [acc memory]
  (if (acc memory)
    (reduced memory)
    (conj acc memory)))

(def part1
  (->> input
    (iterate redistribute-memory)
    (reduce collect-until-duplicate #{})
    count))

(def part2
  (let [duplicate (->> input (iterate redistribute-memory) (reduce collect-until-duplicate' #{}))]
    (->> duplicate
      (iterate redistribute-memory)
      rest
      (take-while #(not= % duplicate))
      count
      inc)))
(do
   (println part1)
   (println part2))
