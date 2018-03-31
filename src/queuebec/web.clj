(ns queuebec.web
  "Expose job submission API on port 3001. All HTTP requests are forwarded
  to backend as jobs."
  (:require
    [queuebec.jobs :as jobs]
    [ring.adapter.jetty :as jetty]
    [ring.middleware.defaults :refer [api-defaults wrap-defaults]]
    [ring.util.response :refer [response]]))

(defn handler
  "Add HTTP request to the job queue and respond to the caller."
  [req]
  (jobs/add req)
  (response "ok"))

(defn start
  "create a new web server and return a function that, when called, will
  terminate the server."
  []
  (let [opts {:join? false :port 3001}
        s (jetty/run-jetty (wrap-defaults handler api-defaults) opts)]
    (fn [] (.stop s))))
