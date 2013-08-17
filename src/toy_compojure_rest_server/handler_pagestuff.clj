(in-ns 'toy-compojure-rest-server.handler)
  (defn show-form []
    (html5
      [:head
        [:title "Create Document"]
        (include-css "//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css")]
      [:body
        [:div {:id "content"} 
            (label "title" "Title")
            (text-field {:id "title"} "title")
            (label "text" "Document to Create")
            (text-area {:id "text"}"text")
            [:button {:id "actionButton"} "Create Document"]]
        [:div {:id "response"}]
        (include-js "//cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js" 
            "//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js")
        (javascript-tag (str "$( document ).ready(function() {"
            "$('#actionButton').click(function() {\n"
                    "var the_title = $('#title').val();\n"
                    "var the_text = $('#text').val();\n"
                    "var the_data = '{ \"text\": \"' + the_text + '\", \"title\": \"' + the_title + '\" }';\n"
                    "$.ajax({ url: '/documents', data: the_data, type: 'POST', dataType: 'json', \n"
                        "contentType: 'application/json',\n"
                        "success: function( json ) { alert('Yay!');},\n"
                        "error: function( json ) { alert('Boo-urns! Message: ' + json.msg);} \n"
                    "});\n"
                "});\n"
            "});"))]))
  
  (defn show-edit-form [document-id]
    (let [doc ((get-document document-id) :body)]
    (html5
      [:head
        [:title (str "Edit Document - " (doc :id))]
        (include-css "//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css")]
      [:body
        [:div {:id "content"} 
            (label "title" "Title")
            (text-field {:id "title"} "title" (doc :title))
            (label "text" "Document to Edit")
            (text-area {:id "text"} "text" (doc :text))
            [:button {:id "actionButton"} "Edit Document"]]
        [:div {:id "response"}]
        (include-js "//cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js" 
            "//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js")
        (javascript-tag (str "$( document ).ready(function() {"
            "$('#actionButton').click(function() {\n"
                    "var the_title = $('#title').val();\n"
                    "var the_text = $('#text').val();\n"
                    "var the_data = '{ \"id\":\"" (:id doc) "\", \"text\": \"' + the_text + '\", \"title\": \"' + the_title + '\" }';\n"
                    "$.ajax({ url: '/documents/" (:id doc) "', data: the_data, type: 'PUT', dataType: 'json', \n"
                        "contentType: 'application/json',\n"
                        "success: function( json ) { alert('Yay!');},\n"
                        "error: function( json ) { alert('Boo-urns! Message: ' + json.msg);} \n"
                    "});\n"
                "});\n"
            "});"))])))
