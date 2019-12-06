(require '[clojure.string :as str])

(defn parse-int [x] (Integer/parseInt x))

(defn apply-instruction [instruction program]
  (def opcode (nth instruction 0))
  (def pos1 (nth instruction 1))
  (def pos2 (nth instruction 2))
  (def outputPos (nth instruction 3))
  (def outputVal (case opcode
    1 (+ (nth program pos1) (nth program pos2))
    2 (* (nth program pos1) (nth program pos2))
    nil))
  
  (assoc program outputPos outputVal))

(defn run [program]
  (loop [i 0 result program]
    (def instruction (take 4 (subvec result (* i 4))))
    (def opcode (nth instruction 0 99))
    (if (= opcode 99)
      result
      (recur (inc i) (apply-instruction instruction result)))))

(defn run-with-params [program p1 p2]
  (run (assoc program 1 p1 2 p2)))

(def program (mapv parse-int (str/split (slurp "./2/input.txt") #",")))
(print (for [x (range 0 100) y (range 0 100) :when (= 19690720 (first (run-with-params program x y)))]
  (+ (* x 100) y)))

