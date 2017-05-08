(ns articles-app.handler
  (:require [articles-app.retrieval-functions :refer [read-rss user-links pretty-top-10-entries my-db]]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [hiccup.page]
            [hiccup.core]))

(def user-rss (map #(read-rss %) (user-links "Jeel Shah" my-db)))

(defn interesting-reads []
  (hiccup.page/html5
   {:lang "en"}
   [:head [:title "Interesting Reads"]]
   [:body [:h1 "Interesting Reads"] (for [x (map #(pretty-top-10-entries %) user-rss)]
                                      x)]))

(defroutes app-routes
  (GET "/" [] (interesting-reads))
  (route/not-found "Sorry, that doesn't make sense."))

(def app
  (wrap-defaults app-routes site-defaults))
