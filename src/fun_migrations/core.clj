(ns fun-migrations.core
  (:require [fun-migrations.sql :as sql]))

(defn- get-name [migration]
  (-> migration meta :name str))

(defn create-sql-driver
  "Creates generic SQL driver.

  It will create/use 'migration' table with 'name' and 'applied_at' columns
  to store applied migrations."
  [db]
  (sql/create-migration-table db)
  (let [is-applied? (sql/get-applied-migrations db)]
    {:migrate #(sql/migrate db (get-name %) %)
     :not-applied? #(-> % get-name is-applied? not)}))

(defn run
  "Runs given migrations using a driver. Already applied migrations are skipped.

  Driver consist of two functions:

    migrate - given not yet applied migration function it applies it
    not-applied? - returns true if given migration has not been applied yet
  "
  [{migrate :migrate
    not-applied? :not-applied?}
   migrations]
  (println "Performing migrations")
  (doseq [m (filter not-applied? migrations)]
    (println "  -" (get-name m))
    (migrate m))
  (println "Done"))
