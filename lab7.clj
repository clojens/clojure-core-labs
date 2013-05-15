; 1
(def dominates {:rock :paper :paper :scissors :scissors :rock})

; 2
(def choices (keys dominates))

; 3
(defn winner [c0 c1]
  (if (= c0 (dominates c1)) c0
        (if (= c1 (dominates c0)) c1 nil)))
  
; 4
(defn draw? [a b]
  (not (winner a b)))

(defn iwon? [a b]
  (= (winner a b) a))

; 5
(defprotocol Player "RPS player"
  (choose [p] "takes a player and returns that player's choice")
  (update-player [p lc olc] "takes a player, that player's last choice, and the
                             other player's last choice, returning a new Player
                             for the next round"))
  
(defrecord Random [choice]
  Player
  (choose [_] (rand-nth choices))
  (update-player [this _ _] this))

; 6
(defrecord Stubborn [choice]
  Player 
  (choose [_] choice) 
  (update-player [this _ _] this))

; 7
(defrecord Mean [last-win]
  Player
  (choose [_]
    (if last-win
        last-win
      (rand-nth choices)))
  (update-player [_ me you]
    (->Mean (when (iwon? me you) me))))

; 8
; bugs here, need to fix p0win
(defn game [p q n]
  (loop [p0 p p1 q s0 0 s1 0 r n]
    (if (<= 0 r) (do (println (str "scores: " (str s0 s1))))
      (let [p0win (iwon? (choose p0) (choose p1))]
        (recur p0 p1 (if p0win (inc s0) s0) (if (not p0win) (inc s1) s1) (dec r))))))

(def mean   (->Mean   [:rock]))
(def random (->Random [:rock]))




