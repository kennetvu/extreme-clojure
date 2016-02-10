(ns extreme-clojure.logging)

(let [log-entries (atom '())]
  (defn add-entry! [entry]
    (swap! log-entries conj entry))
  (defn all-entries []
    @log-entries)
  (defn reset-log! []
    (reset! log-entries [])))
