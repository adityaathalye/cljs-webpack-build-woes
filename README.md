This repo is a minimal clone of this example, to debug dependency woes.
ref: https://github.com/phiresky/sql.js-httpvfs/tree/master#minimal-example-from-scratch

**Install**

```
npm install
cp 
npx shadow-cljs watch app
```
wait for build to complete, otherwise the `target/public/js` folder won't exist. you can of course just create it manually and run the copy first.
```
cp node_modules/sql.js-httpvfs/dist/sql-wasm.wasm target/public/js/
```

open http://localhost:8001