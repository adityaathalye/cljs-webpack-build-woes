(ns core
  (:require
    [shadow.cljs.modern :refer (js-await)]
    ["sql.js-httpvfs" :as vfs]
    ))

(defn query-dummy-example []
  (js-await [worker (vfs/createDbWorker
                      (clj->js [{:from "inline"
                                 :config {:server-mode "full"
                                          :url "/example.sqlite3"
                                          :requestChunkSize 4096}}])
                      "/js/worker.js"
                      "/js/sql-wasm.wasm")]
            (js/console.log "=== querying dummy example ===")
            (js-await [result (-> worker .-db (.exec "select * from mytable"))]
                      (js/console.log "result" result))))

(defn query-haiku []
  (js-await [worker (vfs/createDbWorker
                      (clj->js [{:from "jsonconfig"
                                 :configUrl "/config.json"}])
                      "/js/worker.js"
                      "/js/sql-wasm.wasm")]
            (js/console.log worker)
            (js/console.log "=== querying haiku db ===")
            (js-await [result (-> worker
                                  .-db
                                  ;; (.exec "SELECT * FROM haiku LIMIT 5;")
                                  (.exec "select * from haiku order by random() limit 1"))]
                      (-> js/document
                          (.getElementById "app")
                          (.-innerHTML)
                          (set! (js/JSON.stringify result))))))

(defn ^:dev/after-load init
  []
  (query-dummy-example)
  (query-haiku))

(comment
  (js/console.error "foo")
  )
