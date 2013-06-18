(defproject fun_migrations "0.2.0"
  :description "Database migrations"
  :url "https://github.com/maio/fun_migrations"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/java.jdbc "0.2.3"]]
  :profiles {:dev {:dependencies [[sqlitejdbc "0.5.6"]]}})
