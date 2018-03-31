(ns queuebec.jobs
  "Manage job queue and process incoming jobs."
  (:require
    [bidi.bidi :as bidi]
    [clojure.core.async :refer [>! >!! <! <!! chan close! go go-loop]]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; declarations
(def queue (atom nil))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; job definitions
(defn sum
  "add two numbers together and print to console"
  [req]
  (let [{{:keys [x y]} :params} req]
    (println "SUM:" (apply + (map #(Integer/parseInt %) [x y])))))

(defn subtract
  "subtract two numbers and print to console"
  [req]
  (let [{{:keys [x y]} :params} req]
    (println "SUBTRACT:" (apply - (map #(Integer/parseInt %) [x y])))))

(def router ["/" {"sum" #'sum
                  "subtract" #'subtract}])

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; public api
(defn add
  "add new work to the queue (if one exists)."
  [x]
  (when @queue
    (go (>! @queue {:t :job :msg x}))))

(defn start
  "start a new job queue worker"
  []
  (reset! queue (chan 64))
  (go-loop [{:keys [t msg] :as x} (<! @queue)]
    (case t
      :job (if-let [route (bidi/match-route router (:uri msg))]
             (do
              (try
                (let [f (:handler route)]
                  (f msg))
                (catch Exception ex
                  (println ";; failed" ex)))
              (recur (<! @queue)))
             (println ";; no job matching" (:uri msg)))
      :quit (do
              (close! @queue)
              (reset! queue nil)))))

(defn stop
  "stop a running job queue once it has processed all outstanding work"
  []
  (when @queue
    (go (>! @queue {:t :quit}))))