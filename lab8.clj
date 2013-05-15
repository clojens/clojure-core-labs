(use 'clojure.pprint)
(use 'clojure.repl)

; 1
(defmacro just-do-it [& body]
  `(do ~@body))
  
; 2
(defmacro execute [x]
  `(do (println '~x)
       (println  ~x)))

; 3
(defmacro pretty-execute [x]
  `(do (clojure.pprint/pprint '~x)
       (clojure.pprint/pprint  ~x)))
  
; 4
; (defmacro safe-execute [x]
  
; 5
(defmacro transcript
  ([] 
    nil)
  ([x & tail]
    `((execute    ~x) 
      (transcript ~@tail))))

