(ns student.dialect :reload)

(defn canadianize [x]
  (let [y (clojure.string/replace x #"\." "")]
       (str y ", eh?")))

