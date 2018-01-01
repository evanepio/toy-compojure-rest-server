(ns toy-compojure-rest-server.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
            [toy-compojure-rest-server.routes :refer [document-routes]]))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (context "/documents" [] document-routes)
  (route/not-found "Not Found"))

(def app
  (-> (wrap-defaults app-routes site-defaults)
      wrap-json-response
      wrap-json-body))
