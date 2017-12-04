(ns day4.core)

(defn parse-input
  [text]
  (->> text
   (clojure.string/split-lines)
   (map #(clojure.string/split % #"\s"))
   ))

(def input (parse-input (slurp "input.txt")))

(defn valid-passphrase?
  [phrase]
  (= (count phrase) (count (distinct phrase))))

(defn valid-passphrase-with-anagrams?
  [phrase]
  (let [sorted-words (map sort phrase)]
    (= (count sorted-words) (count (distinct sorted-words)))))

(defn part1
  []
  (->>
    input
    (filter valid-passphrase?)
    count))

(defn part2
  []
  (->>
    input
    (filter valid-passphrase-with-anagrams?)
    count))

(do
   (println (part1))
   (println (part2)))
