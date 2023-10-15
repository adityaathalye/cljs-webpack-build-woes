This repo is a minimal clone of this example, to debug dependency woes.
ref: https://github.com/phiresky/sql.js-httpvfs/tree/master#minimal-example-from-scratch

**Install**

Copied from the sql.js-httpvfs README.
```
npm install --save-dev webpack webpack-cli typescript ts-loader http-server
npm install --save sql.js-httpvfs
npx tsc --init
```

**Build**

Compile our Cljs to `target/index.js`.

```
npx shadow-cljs compile app
```

Use the `target/index.js` to build the project (pull deps).
```
npx webpack --mode=development
```

At which point, the webpack build results in this error:
```
asset 51ea95f2f6828531d309.module.wasm 1.18 MiB [emitted] [immutable] (auxiliary name: main)
asset bundle.js 103 KiB [emitted] (name: main)
runtime modules 1.64 KiB 3 modules
cacheable modules 90.5 KiB (javascript) 1.18 MiB (webassembly)
  modules by path ./node_modules/sql.js-httpvfs/dist/*.js 89.5 KiB
    ./node_modules/sql.js-httpvfs/dist/sqlite.worker.js 81.8 KiB [built] [code generated]
    ./node_modules/sql.js-httpvfs/dist/index.js 7.73 KiB [built] [code generated]
  ./target/index.js 605 bytes [built] [code generated]
  ./node_modules/sql.js-httpvfs/dist/sql-wasm.wasm 400 bytes (javascript) 1.18 MiB (webassembly) [built] [code generated]

ERROR in ./node_modules/sql.js-httpvfs/dist/sql-wasm.wasm
Module not found: Error: Can't resolve 'a' in '/home/adi/src/github/adityaathalye/cljs-webpack-build-woes/node_modules/sql.js-httpvfs/dist'
resolve 'a' in '/home/adi/src/github/adityaathalye/cljs-webpack-build-woes/node_modules/sql.js-httpvfs/dist'
  Parsed request is a module
  using description file: /home/adi/src/github/adityaathalye/cljs-webpack-build-woes/node_modules/sql.js-httpvfs/package.json (relative path: ./dist)
    Field 'browser' doesn't contain a valid alias configuration
    resolve as module
      /home/adi/src/github/adityaathalye/cljs-webpack-build-woes/node_modules/sql.js-httpvfs/dist/node_modules doesn't exist or is not a directory
      /home/adi/src/github/adityaathalye/cljs-webpack-build-woes/node_modules/sql.js-httpvfs/node_modules doesn't exist or is not a directory
      /home/adi/src/github/adityaathalye/cljs-webpack-build-woes/node_modules/node_modules doesn't exist or is not a directory
      looking for modules in /home/adi/src/github/adityaathalye/cljs-webpack-build-woes/node_modules
        single file module
          using description file: /home/adi/src/github/adityaathalye/cljs-webpack-build-woes/package.json (relative path: ./node_modules/a)
            no extension
              Field 'browser' doesn't contain a valid alias configuration
              /home/adi/src/github/adityaathalye/cljs-webpack-build-woes/node_modules/a doesn't exist
            .tsx
              Field 'browser' doesn't contain a valid alias configuration
              /home/adi/src/github/adityaathalye/cljs-webpack-build-woes/node_modules/a.tsx doesn't exist
            .ts
              Field 'browser' doesn't contain a valid alias configuration
              /home/adi/src/github/adityaathalye/cljs-webpack-build-woes/node_modules/a.ts doesn't exist
            .js
              Field 'browser' doesn't contain a valid alias configuration
              /home/adi/src/github/adityaathalye/cljs-webpack-build-woes/node_modules/a.js doesn't exist
        /home/adi/src/github/adityaathalye/cljs-webpack-build-woes/node_modules/a doesn't exist
      /home/adi/src/github/adityaathalye/node_modules doesn't exist or is not a directory
      /home/adi/src/github/node_modules doesn't exist or is not a directory
      /home/adi/src/node_modules doesn't exist or is not a directory
      /home/adi/node_modules doesn't exist or is not a directory
      /home/node_modules doesn't exist or is not a directory
      /node_modules doesn't exist or is not a directory
 @ ./target/index.js 5:43-87
```
