# fun_migrations

A Clojure library designed to perform (not only) database migrations.
Each migration is regular Clojure function so you are not limited to
SQL as with other migration libraries.

## Usage

```
(ns your-app.migrations
  (:require [fun-migrations.core :as fm]))

(def db ...)   ;; db-spec compatible with cojure.jdbc

(def migrations
  [(defn some-migration [] ...)
   (defn other-migration [] ...)])

(defn -main []
  (fm/run (fm/create-sql-driver db) migrations))
```

```
lein run -m your-app.migrations
```

## License

Copyright © 2013 Marian Schubert

Distributed under the Eclipse Public License, the same as Clojure.
