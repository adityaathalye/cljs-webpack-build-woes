const path = require('path');

module.exports = {
    entry: './target/index.js',
    module: {
        // copied over from the reference typescript example
        rules: [
            {
                test: /\.tsx?$/,
                use: "ts-loader",
                exclude: path.resolve(__dirname, './node_modules/'),
            },
        ],
    },
    resolve: {
        // copied over from the reference typescript example
        extensions: [".tsx", ".ts", ".js"],
    },
    output: {
        path: path.resolve(__dirname, './target/public/js'),
        filename: 'bundle.js',
        wasmLoading: 'fetch',
    },
    // "Since webpack 5 WebAssembly is not enabled by default and flagged
    // as experimental feature." You need to enable one of the WebAssembly
    // experiments via 'experiments.asyncWebAssembly: true'"
    //   - Said the webpack build error message.
    experiments: {
        asyncWebAssembly: true
    },
    devServer: {
        publicPath: './target/public/js',
    },
};
