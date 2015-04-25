(defproject fun_migrations "0.3.0"
  :description "Database migrations"
  :url "https://github.com/maio/fun_migrations"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/java.jdbc "0.3.6"]]
  :profiles {:dev {:dependencies [[sqlitejdbc "0.5.6"]]}})
