    (ns clojure-rest.handler
      (:import com.mchange.v2.c3p0.ComboPooledDataSource)
      (:use compojure.core)
      (:use cheshire.core)
      (:use ring.util.response)
      (:use [hiccup.page :only (html5 include-css include-js)])
      (:use [hiccup.element :only (javascript-tag)])
      (:use [hiccup.form :only (form-to label text-area text-field submit-button)])
      (:require [compojure.handler :as handler]
                [ring.middleware.json :as middleware]
                [clojure.java.jdbc :as sql]
                [compojure.route :as route]
                [clj-http.client :as client]))
    
    (load "handler_dbstuff")
    (load "handler_pagestuff")

    (defroutes app-routes
      (context "/documents" [] (defroutes documents-routes
        (GET  "/" [] (get-all-documents))
        (POST "/" {body :body} (create-new-document body))
        (context "/:id" [id] (defroutes document-routes
          (GET    "/" [] (get-document id))
          (PUT    "/" {body :body} (update-document id body))
          (DELETE "/" [] (delete-document id))))))
      (context "/insert-record" [] (defroutes insert-routes
        (GET "/" [] (create-new-document {:title "Hello!" :text "This is Sparta."}))))
      (context "/do-post" [] (defroutes posting-routes
        (GET "/" [] (client/post "http://localhost:3000/documents/"
                      {:body "{\"title\": \"jsonTitle\",\"text\": \"jsonText goes here. Waka waka!\"}"
                       :headers {"X-Api-Version" "2"}
                       :content-type :json
                       :socket-timeout 1000  ;; in milliseconds
                       :conn-timeout 1000    ;; in milliseconds
                       :accept :json}))))
      (context "/show-form" [] (defroutes show-form-routes
        (GET "/" [] (show-form))
        (GET "/:id" [id] (show-edit-form id))))
      (route/not-found "Not Found"))

    (def app
        (-> (handler/api app-routes)
            (middleware/wrap-json-body)
            (middleware/wrap-json-response)))

