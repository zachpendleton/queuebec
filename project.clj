(defproject queuebec "0.1.0-SNAPSHOT"
  :description "Work queueing with an HTTP interface"
  :url "https://github.com/zachpendleton/queuebec"
  :license {:name "MIT"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [bidi "2.1.3"]
                 [ring/ring-core "1.6.3"]
                 [ring/ring-defaults "0.3.1"]
                 [ring/ring-jetty-adapter "1.6.3"]
                 [org.clojure/core.async "0.4.474"]]
  :main ^:skip-aot queuebec.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
