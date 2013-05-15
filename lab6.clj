; initial code

(defn patient []
  (ref {:arms  2
        :legs  2
        :heads 1}))

(defn init-patients []
  (vec (repeatedly 1000 patient)))

(defn surgeon [specialty] 
  (agent {:specialty specialty}))

(defn init-surgeons []
  (vec (take 100 (map surgeon (cycle [:arms :legs :heads])))))

(def all-patients nil)
(def all-surgeons nil)

(defn init! []
  (alter-var-root #'all-patients (constantly (init-patients)))
  (alter-var-root #'all-surgeons (constantly (init-surgeons))))

(defn totals []
  (dosync
    (reduce (partial merge-with +)
            (map deref all-patients))))

; 2
(defn check-part [p l] (> (get p l) 0))

(defn transplant! [s p0 p1]
  (let [spec (:specialty s)]       
    (when (check-part @p0 spec)
        (dosync 
          ((alter p0 update-in [spec] dec)
           (alter p1 update-in [spec] inc)))))
        s)

; 3
(defn select-patients []
  (let [n1 (rand (count all-patients))
        n2 (mod (inc n1) (count all-patients))]
    [(nth all-patients n1) (nth all-patients n2)]))

(defn operate! []
  (let [surgn (rand-nth all-surgeons)
        [p1 p2] (select-patients)]
    (send surgn (transplant! surgn p1 p2))))

; 4
(defn operating-room []
  (future
    (dotimes [_ 10000] (operate!))))

; 5
(defn auditor []
  (future
    (dotimes [_ 10]
      (println (totals)))))

; (defn run! []
;   (init!)
;   (println "Finding the variant:")
;   (let [audit (auditor)
;         rooms (doall (repeatedly 5 operating-room))]
;     (dorun (map deref rooms))
;     (deref audit)))

(defn run! []
  (init!)
  (let [audit (auditor)]
    (dotimes [_ 10000] (operate!))
    (apply await all-surgeons)
    (deref audit)))


(init!)
(run!)

