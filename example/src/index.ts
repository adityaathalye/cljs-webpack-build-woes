import { createDbWorker } from "sql.js-httpvfs";
import { SplitFileConfig } from "sql.js-httpvfs/dist/sqlite.worker";

const workerUrl = new URL(
  "sql.js-httpvfs/dist/sqlite.worker.js",
  import.meta.url
);

const wasmUrl = new URL(
    "sql.js-httpvfs/dist/sql-wasm.wasm",
    import.meta.url);

const configInline = {
    from: "inline",
    config: {
        serverMode: "full",
        url: "/data/all_haiku.sqlite3",
        requestChunkSize: 4096,
    },
};

const configJson: SplitFileConfig[] = [{
    from: "jsonconfig",
    configUrl: "/data/config.json",
}];

// let maxBytesToRead = 16 * 1024 * 1024; // set it larger than the DB

async function load(cfg: SplitFileConfig[]) {
  const worker = await createDbWorker(
      cfg,
      workerUrl.toString(),
      wasmUrl.toString(),
      // maxBytesToRead // read to infinity when not used
  );

    // const result = await worker.db.query(
    //     `select * from haiku limit 5`
    // );

    const result = await worker.db.query(
        `select * from haiku order by random() limit 1`
    );

  document.body.textContent = JSON.stringify(result);
}

// load(configInline);
load(configJson);
