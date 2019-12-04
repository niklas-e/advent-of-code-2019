(require '[clojure.string :as str])

(defn parse-int [value] (Integer/parseInt value))

(defn calculate-module [value]
  (def divided (/ value 3))
  (def rounded (int (Math/floor divided)))
  (- rounded 2))

(defn calculate-recursively [value]
  (loop [x value total 0]
    (def result (calculate-module x))
    (if (<= result 0)
      total
      (recur result (+ total result)))))

(def rawInput (slurp "./1/input.txt"))
(def inputs (map parse-int (str/split rawInput #"\n")))
(print (reduce + 0 (map calculate-recursively inputs)))
