(ns fun-migrations.sql
  (:require [clojure.java.jdbc :as sql]))

(defn- mark-as-applied [db name]
  (sql/with-connection db
    (sql/insert-record :migration {:name name})))

(defn migrate [db name f]
  (f)
  (mark-as-applied db name))

(defn get-applied-migrations [db]
  (sql/with-connection db
    (sql/with-query-results results ["select name from migration"]
      (set (map :name results)))))

(defn create-migration-table [db]
  (sql/with-connection db
    (try
      (sql/create-table :migration
                        [:name :varchar "PRIMARY KEY"]
                        [:applied_at :timestamp "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"])
      (catch java.sql.SQLException _))))
