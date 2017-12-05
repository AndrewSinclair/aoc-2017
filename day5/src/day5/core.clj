(ns day5.core)

(defn parse-input
  [text]
  (->> text
       clojure.string/split-lines
       (map read-string)
       vec))

(def input (parse-input (slurp "input.txt")))

(defn step-algorithm
  [step-logic]
  (fn [[i input]]
    (let [offset (nth input i)]
      (vector
        (+ i offset)
        (update input i step-logic)))))

(def step-logic-1 inc)

(defn step-logic-2
  [offset]
  (if (< offset 3) (inc offset) (dec offset)))

(defn not-exit?
  [[i input]]
  (< i (count input)))

(defn maze-steps
  [step]
  (->>
    (iterate step [0 input])
    (take-while not-exit?)))

(do
  (println (count (maze-steps (step-algorithm step-logic-1))))
  (println (count (maze-steps (step-algorithm step-logic-2)))))
