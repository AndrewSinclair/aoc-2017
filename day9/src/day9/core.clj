(ns day9.core)

(defn str-remove
  [regex text]
  (clojure.string/replace text regex ""))

(defn str-replace
  [regex replacement text]
  (clojure.string/replace text regex replacement))

(defn parse-input-1
  [text]
  (->> text
    (str-remove #"!.")
    (str-remove #"<.*?>")
    (str-remove #",")))

(defn parse-input-2
  [text]
  (let [with-garbage (->> text (str-remove #"!."))
        no-garbage   (->> with-garbage (str-replace #"<.*?>" "--"))]
    (- (count with-garbage) (count no-garbage))))

(def input (parse-input-1 (slurp "input.txt")))

(defn consume-paren
  [i paren]
  (if (= paren \{)
      (inc i)
      (dec i)))

(def part1
  (->>
    (map
      (fn [a b] (vector a b))
      (->> input (reductions consume-paren 1))
      (->> input (map #(= \{ %))))
    (filter second)
    (map first)
    (reduce +)))

(def part2 (parse-input-2 (slurp "input.txt")))

(do
   (println part1)
   (println part2))
