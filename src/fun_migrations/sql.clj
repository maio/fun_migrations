(ns fun-migrations.sql
  (:require [clojure.java.jdbc :as sql]))

(defn- mark-as-applied [db name]
  (sql/insert! db :migrations {:name name}))

(defn migrate [db name f]
  (f)
  (mark-as-applied db name))

(defn get-applied-migrations [db]
  (set (map :name (sql/query db "select name from migrations"))))

(defn create-migration-table [db]
  (try
    (sql/db-do-commands
     db (sql/create-table-ddl :migrations
                              [:name :varchar "PRIMARY KEY"]
                              [:applied_at :timestamp "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"]))
    (catch java.sql.SQLException _)))
