(ns extreme-clojure.handler
  (:require [compojure.core :refer :all]
            [clojure.string :as str]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defn comma-string-to-list
  [string]
  (str/split string #", "))


(defn split-string
  [string]
  (str/split string #": " 2))

(defn split-string-space
  [string]
  (str/split string #" "))



(defn largest
  "which of the following numbers is the largest:"
  [numbers]
  (let [x (comma-string-to-list numbers)]
    (apply max (map read-string x)))
)

(defn plus
  "what is 18 plus 0"
  [xs ys]
  (let [x (read-string xs)
        y (read-string ys)]
  (+ x y)))



(defn question-parser 
  "Parse question and return function"
  [[id question]]
  (cond
    (.contains question "largest:") (str (largest (nth (split-string question) 1)))
    (.contains question "plus") (let
                                    [x (get (split-string-space question) 2)
                                     y (get (split-string-space question) 4)]
                                  (str (plus x y)))
    :else question)
  )



(defn handler 
 [request]
  (let
      [question (str request)]
    (print (str (get (split-string question) 1) " - " ))
    (println (question-parser (split-string question)))
    (question-parser (split-string question))
    ))




(defroutes app-routes
  (GET "/" [q] (handler (str q)))
)
;  (GET "/" {{q :q} :params} (do(println q) "kkv")))
(def app
  (wrap-defaults app-routes site-defaults))
