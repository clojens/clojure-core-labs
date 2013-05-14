; 1
(defn greet0 [] 
  (println "hello"))

; 2
(def greet1 (fn [] (println "hello")))
(def greet2 #(println "hello"))

; 3
(defn greeting 
  ([]
    (greeting "hello" "world"))
  ([x]
    (greeting "hello" x))
  ([x y]
    (str (str x ", ") y)))

; 4
(defn do-nothing [x] x)

; 5
(defn always-thing [& args] :thing)

; 6
(defn make-thingy [x] 
  (fn [& args] x))

; 7
(defn triplicate [f]
  (f) (f) (f))

; 8
(defn opposite [f]
  (fn [& args] (not (apply f args))))

; 9
(defn triplicate2 [f & args]
  (triplicate (apply f args)))

; 10
(Math/cos Math/PI)
(defn trigdemo [x] 
  (+ (Math/pow (Math/cos x) 2)
     (Math/pow (Math/sin x) 2))) 

; 11
(defn fetch [url]
  (slurp (.openStream (java.net.URL. url))))

; 12
