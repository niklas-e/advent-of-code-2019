(require '[clojure.string :as str])

(defn calculate-module [value]
  (def number (Integer/parseInt value))
  (def divided (/ number 3))
  (def rounded (int (Math/floor divided)))
  (- rounded 2))

(def rawInput (slurp "./1/input.txt"))
(def inputs (str/split rawInput #"\n"))
(print (reduce + 0 (map calculate-module inputs)))
