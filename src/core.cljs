(ns core
  (:require
   ;; ref: "Using npm packages"
   ;; https://shadow-cljs.github.io/docs/UsersGuide.html#_using_npm_packages
   ;; - see example: import { thing } from "foo.bar";
   ;; - see heading: including a Module for side-effects only
   ["sql.js-httpvfs" :refer (createDbWorker)] ; this gets bundled
   ["sql.js-httpvfs/dist/sqlite.worker.js"] ; this gets bundled
   ["sql.js-httpvfs/dist/sql-wasm.wasm"] ; this gets copied over
   ))

(def worker-url
  (js/URL. "js/bundle.js", js/document.URL))

#_(def wasm-url
  (js/URL. "js/", js/document.URL))

#_(def db-worker
  (sjs/createDbWorker
    (clj->js [{:from "inline"
               :config {:server-mode "full"
                        :url "/example.sqlite3"
                        :requestChunkSize 4096}}])
    (.toString worker-url)
    (.toString wasm-url)))

#_(js/console.log db-worker)

(comment
  (js/console.error "foo")
  )
