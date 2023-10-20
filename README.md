This repo is a minimal Cljs clone of this example, to debug dependency woes.
ref: https://github.com/phiresky/sql.js-httpvfs/tree/master#minimal-example-from-scratch

phiresky's sample code is copied here in the `example/` dir for comparison.

Additionally, I have used a real-world data set in `example/data`, created
using the instructions in phiresky's README.

Both the TS, and Cljs code use the same random-access query that may or
may not have to touch the last record. The TS code "just works", but the
Cljs code fails with this error:

```
Uncaught (in promise) TypeError: e is undefined
    SplitFileHttpDatabase http://localhost:8001/js/cljs-runtime/module$node_modules$sql_DOT_js_httpvfs$dist$sqlite_worker.js:25
```
It seems to originate in the `sql_DOT_js-httpvfs/dist/index.js` file.

**Install**

The ClojureScript project:
```
npm install
npx shadow-cljs watch app
cp node_modules/sql.js-httpvfs/dist/sql-wasm.wasm resources/public
```
open http://localhost:8001 and check developer console for log of query results

The Typescript Example project:
```
cd example
npm install
npx webpack --mode=development && npx http-server
```

open http://localhost:8080 and refresh the page update query results (if you're
using the random query)
