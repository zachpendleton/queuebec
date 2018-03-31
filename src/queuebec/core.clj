(ns queuebec.core
  (:require
    [queuebec.jobs :as jobs]
    [queuebec.web :as web])
  (:gen-class))

(defn -main
  [& args]
  (println ";; starting on 3001")
  (jobs/start)
  (web/start))