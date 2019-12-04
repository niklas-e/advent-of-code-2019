(require '[clojure.string :as str])

(defn parse-int [x] (Integer/parseInt x))

(defn apply-opcode [opcode program]
  (def cmd (nth opcode 0))
  (def pos1 (nth opcode 1))
  (def pos2 (nth opcode 2))
  (def outputPos (nth opcode 3))
  (def outputVal (case cmd
    1 (+ (nth program pos1) (nth program pos2))
    2 (* (nth program pos1) (nth program pos2))
    nil))
  
  (assoc program outputPos outputVal))

(defn run [program]
  (loop [i 0 result program]
    (def opcode (take 4 (subvec result (* i 4))))
    (def cmd (nth opcode 0 99))
    (if (= cmd 99)
      result
      (recur (inc i) (apply-opcode opcode result)))))

(def program (mapv parse-int (str/split (slurp "./2/input.txt") #",")))
(print (run program))
