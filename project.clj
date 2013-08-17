(defproject toy-compojure-rest-server "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/java.jdbc "0.2.3"]
                 [clj-http "0.7.6"]
                 [postgresql "9.1-901.jdbc4"]
                 [c3p0 "0.9.1.2"]
                 [compojure "1.1.5"]
                 [cheshire "5.2.0"]
                 [hiccup "1.0.4"]
                 [ring/ring-json "0.2.0"]
                 [ring/ring-jetty-adapter "1.2.0"]]
  :plugins [[lein-ring "0.8.5"]]
  :ring {:handler toy-compojure-rest-server.handler/app}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.5"]]}})
