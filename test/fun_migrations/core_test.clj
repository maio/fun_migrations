(ns fun-migrations.core-test
  (:require [clojure.test :refer :all]
            [fun-migrations.core :as fm]))

(defn new-temp-db []
  (str "jdbc:sqlite:"
       (doto (java.io.File/createTempFile "test" ".db") .deleteOnExit)))

(def ^:dynamic *log*)
(defn migration-x [] (swap! *log* conj :ran-x))
(defn migration-y [] (swap! *log* conj :ran-y))

(defmacro run [db migrations]
  `(binding [*log* (atom [])]
     (with-out-str (fm/run (fm/create-sql-driver ~db) ~migrations))
     @*log*))

(deftest fun-migrations
  (testing "apply migrations"
    (let [db (new-temp-db)]
      (is (= [:ran-x :ran-y] (run db [#'migration-x #'migration-y])))))

  (testing "skip already applied migrations"
    (let [db (new-temp-db)]
      (is (= [:ran-x] (run db [#'migration-x])))
      (is (= [:ran-y] (run db [#'migration-x #'migration-y]))))))
