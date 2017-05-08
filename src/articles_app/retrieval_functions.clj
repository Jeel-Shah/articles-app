(ns articles-app.retrieval-functions
  (:require [feedme]
            [clojure.java.jdbc :as sql]
            [hiccup.core]))

(def my-db {:classname "org.sqlite.JDBC" :subprotocol "sqlite" :subname "db.db"})

(defn read-rss [rss-url]
  (feedme/parse rss-url))

(defn user-links [user-name db]
  (-> (sql/query db ["select links from users where name=?" user-name])
      first
      :links
      read-string))

(defn prettify-rss-entry [rss-entry]
  (let [title (:title rss-entry) link (:link rss-entry) date (:published rss-entry)]
    (hiccup.core/html
     [:div {:class "article"}
      [:h2 {:class "article-title"} [:a {:href link} title]]
      [:p {:class "article-date"} date]])))

(defn pretty-top-10-entries [rss-entries]
  (take 10
        (map prettify-rss-entry rss-entries)))
