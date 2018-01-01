(ns toy-compojure-rest-server.routes
  (:require [compojure.core :refer :all]
            [toy-compojure-rest-server.documents :refer [get-all-documents]]))

(defroutes document-routes
  (GET "/" [] (get-all-documents)))
