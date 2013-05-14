; 1
(def fibs
  (map first
    (iterate (fn [[a b]] [b (+ a b)])
             [0 1])))

(reduce + (take 50 fibs))

; 2
(apply + (take 50 fibs))

; 3
(def primes
  (letfn [(next-prime [known-primes n]
            (lazy-seq
             (if (some #(zero? (rem n %)) known-primes)
               (next-prime known-primes (inc n))
               (cons n (next-prime (conj known-primes n) (inc n))))))]
    (next-prime [] 2)))

(reduce + (take 50 (filter #(> % 100) primes)))

; 4
(def alphabet (map char (range (int \A) (inc (int \Z)))))

; 5
(def rot13             (take 26 (drop 13 (cycle alphabet))))

; 6
(defn rotate [c n]
  (take (count c) (drop n (cycle c))))

; 7
(def rot13pairs (map (fn [a b] [a b]) alphabet rot13))

; 8
(into #{} rot13pairs)

; 9
(def rot13-cipher (zipmap alphabet rot13))

; 10
(defn rot13-one-char [c]
  (if (contains? rot13-cipher c) (rot13-cipher c)
                                 c))

; 11
(defn rot13 [string]
  (apply str (map rot13-one-char string))) 

; 12
(def secret-message
  "FCMJ C CM U JLIALUGGCHA MSMNYG ZIL NBY CVG 704 ZIL WIGJONCHA QCNB
MSGVIFCW YRJLYMMCIHM. CN BUM VYYH OMYX ZIL MSGVIFCW WUFWOFUNCIHM CH
XCZZYLYHNCUF UHX CHNYALUF WUFWOFOM, YFYWNLCW WCLWOCN NBYILS,
GUNBYGUNCWUF FIACW, UHX ULNCZCWCUF CHNYFFCAYHWY.")

(defn count-letters [string] 
  (frequencies string))

(defn count-letters0 [string]
  (reduce 
    (fn [result item] (assoc result item (+ 1 (get result item 0))))
     {} string))
        
; 13
(defn count-letters-fnil [string]
  (reduce 
    (fn [result item] (update-in result [item] (fnil #(+ 1 %) 0)))
     {} string))

; 14
(def common-letters (sort-by second (count-letters-fnil secret-message)))

; 15
; ABCDEFGHIJKLMNOPQRSTUVWXYZ -- 6-space difference

(def other (rotate alphabet 6))

(def other-pairs (conj (map (fn [a b] [a b]) alphabet other) [\space \space]))

(def other-cipher (zipmap alphabet other))

(defn other-cipher-one-char [c]
  (if (contains? other-cipher c) (other-cipher c)))

(defn other [string]
  (apply str (map other-cipher-one-char string))) 


