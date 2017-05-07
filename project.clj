(defproject articles-app "0.1.0-SNAPSHOT"
  :description "A simple web app for me to get all of my favorite news"
  :url "http://ohlogn.com"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [ring/ring-defaults "0.2.1"]
                 [feedme "0.0.3"]
                 [hiccup "1.0.5"]
                 [org.clojure/java.jdbc "0.3.7"]
                 [org.xerial/sqlite-jdbc "3.7.2"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler articles-app.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
