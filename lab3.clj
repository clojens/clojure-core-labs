; 1
(defn check-guess [secret guess]
  (if (= secret guess) "you win!"
      (if (< guess secret) "too low." "too high.")))

; 2
(defn cond-check-guess [secret guess]
  (cond (= secret guess) "you win!"
        (< guess secret) "too low."
        :else            "too high."))

; 3
(defn triplicate [f]
  (dotimes [i 3] (f)))

; 4
(defn numbers [n]
  (dotimes [i n]
    (println i)))
 
; 5
(defn counting [n]
  (doseq [i (range 1 (+ n 1))]
    (println i)))

; 6
(defn print-bands [guitars basses drums]
  (doseq [gs guitars
          bs basses
          ds drums]
   (println gs bs ds)))

; 7
(defn all-bands [guitars basses drums]
  (for [gs guitars 
        bs basses 
        ds drums]
       [gs bs ds]))

; 8
(defn fizzbuzz []
  (doseq [n (range 1 (+ 100 1))]
         (cond 
           (= 0 (rem n 15)) (println "fizzbuzz")
           (= 0 (rem n 3))  (println "fizz")
           (= 0 (rem n 5))  (println "buzz")
           :else           (println n))))

; 9
(defn euclid [a0 b0]
  (loop [a a0 b b0]
    (cond (= a 0) b
          (= b 0) a
          :else  (if (> a b) (recur (- a b) b)
                             (recur a (- b a))))))

; 10
(defn guessing-game []
  (let [n (rand-int 10)]
       (fn [g] (if (= g n) (println "you win!")
                           (println "you lose.")))))
 
; 11
; (defn binary-search [n nums]
;   (loop [ms nums p (/ (count nums) 2)]
;     (cond 
;       (= (nth ms p) n) true
;       (> (nth ms p) n) (recur (take p ms) (/ p 2))


