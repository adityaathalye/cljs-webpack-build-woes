(ns core
  (:require
    [shadow.cljs.modern :refer (js-await)]
    ["sql.js-httpvfs" :as vfs]
    ))

(defn init []
  (js-await [worker (vfs/createDbWorker
                      (clj->js [{:from "inline"
                                 :config {:server-mode "full"
                                          :url "/example.sqlite3"
                                          :requestChunkSize 4096}}])
                      "/js/worker.js"
                      "/sql-wasm.wasm")]
    (js-await [result (-> worker .-db (.exec "select * from mytable"))]
      (js/console.log "result" result))))

#_(js/console.log db-worker)

(comment
  (js/console.error "foo")
  )
