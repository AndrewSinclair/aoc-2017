(ns day3.core)

(def input 312051)

(defn round-up-odd
  [x]
  (let [n (int (Math/ceil x))]
    (if (odd? n) n (inc n))))

(defn **2
  [n]
  (* n n))

(defn max-outer-ring
  [n]
  (-> n
    (Math/sqrt)
    (round-up-odd)
    **2))

(defn ring-num
  [n]
  (-> n
    (Math/sqrt)
    (round-up-odd)
    inc
    (quot 2)
    dec))

(defn side-length
  [n]
  (- (* (inc (ring-num n)) 2) 2))

(defn max-num-on-same-side
  [n]
  (->> n
    max-outer-ring
    (iterate (fn [a] (- a (side-length n))))
    (take-while #(<= n %))
    last))

(defn mid-point-val
  [n]
  (->
    (-
     (max-num-on-same-side n)
     (side-length n))
    (+ (quot (side-length n) 2))))

(defn next-coord
  [[x y]]
  ; TODO
  ([x y]))

(def part1
 (+
   (ring-num input)
   (Math/abs (- (mid-point-val input) input))))

(defn get-sum-neighbors
  [[x y]]
  (vector
    [x y]
    (+
      (get [x (inc y)] grid)
      (get [x (dec y)] grid)
      (get [(inc x) y] grid)
      (get [(dec x) y] grid)
      (get [(inc x) (inc y)] grid)
      (get [(inc x) (dec y)] grid)
      (get [(dec x) (inc y)] grid)
      (get [(dec x) (dec y)] grid))))

(defn update-value!
  
  )

(def part2
  (->> [0 0]
       (iterate next-coord)
       (map get-sum-neighbors)
       (map #(apply + %))
       update-value!
       (filter #(< input %))
       first))

(do
 (println part1)
 (println part2))
