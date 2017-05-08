(ns articles-app.handler
  (:require [articles-app.retrieval-functions :as r-functions]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [hiccup.page]))

(defn interesting-reads []
  (hiccup.page/html5
   {:lang "en"}
   [:head [:title "Interesting Reads"]]
   [:body (r-functions/pretty-top-10-entries (:entries (r-functions/read-rss "http://news.ycombinator.com/rss")))]))

(defroutes app-routes
  (GET "/" [] (interesting-reads))
  (route/not-found "Sorry, that doesn\'t make sense."))

(def app
  (wrap-defaults app-routes site-defaults))
