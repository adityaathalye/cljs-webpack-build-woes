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
                      (clj->js [{:from "jsconfig"
                                 :configUrl "/config.json"}])
                      "/js/worker.js"
                      "/js/sql-wasm.wasm")]
            (js/console.log worker)
            #_(js/console.log "=== querying haiku db ===")
            #_(js-await [result (-> worker
                                    .-db
                                    (.exec "SELECT * FROM haiku LIMIT 5;"))]
                        (js/console.log result))))

(defn ^:dev/after-load init
  []
  (query-dummy-example)
  (query-haiku))

(comment
  (js/console.error "foo")
  )
