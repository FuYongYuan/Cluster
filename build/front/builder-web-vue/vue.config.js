module.exports = {
    devServer: {
        proxy: {
            "/member": {
                target: "http://localhost:8001/", // 后台接口域名
                secure: false, // false为http访问，true为https访问
                changeOrigin: true, // 跨域访问设置，true代表跨域
                ws: false, //如果要代理 websockets，配置这个参数
                pathRewrite: {
                    // 路径改写规则
                    "^/member": "", // 以/proxy/为开头的改写为''
                },
            },
            "/authorization": {
                target: "http://localhost:8002/", // 后台接口域名
                secure: false, // false为http访问，true为https访问
                changeOrigin: true, // 跨域访问设置，true代表跨域
                ws: false, //如果要代理 websockets，配置这个参数
                pathRewrite: {
                    // 路径改写规则
                    "^/authorization": "", // 以/proxy/为开头的改写为''
                },
            },
            "/capability": {
                target: "http://localhost:8003/", // 后台接口域名
                secure: false, // false为http访问，true为https访问
                changeOrigin: true, // 跨域访问设置，true代表跨域
                ws: false, //如果要代理 websockets，配置这个参数
                pathRewrite: {
                    // 路径改写规则
                    "^/capability": "", // 以/proxy/为开头的改写为''
                },
            },
            "/dictionary": {
                target: "http://localhost:8004/", // 后台接口域名
                secure: false, // false为http访问，true为https访问
                changeOrigin: true, // 跨域访问设置，true代表跨域
                ws: false, //如果要代理 websockets，配置这个参数
                pathRewrite: {
                    // 路径改写规则
                    "^/dictionary": "", // 以/proxy/为开头的改写为''
                },
            },
            "/data": {
                target: "http://localhost:8005/", // 后台接口域名
                secure: false, // false为http访问，true为https访问
                changeOrigin: true, // 跨域访问设置，true代表跨域
                ws: false, //如果要代理 websockets，配置这个参数
                pathRewrite: {
                    // 路径改写规则
                    "^/data": "", // 以/proxy/为开头的改写为''
                },
            },
            "/message": {
                target: "http://localhost:8006/", // 后台接口域名
                secure: false, // false为http访问，true为https访问
                changeOrigin: true, // 跨域访问设置，true代表跨域
                ws: false, //如果要代理 websockets，配置这个参数
                pathRewrite: {
                    // 路径改写规则
                    "^/message": "", // 以/proxy/为开头的改写为''
                },
            },
            "/builder": {
                target: "http://localhost:8007/", // 后台接口域名
                secure: false, // false为http访问，true为https访问
                changeOrigin: true, // 跨域访问设置，true代表跨域
                ws: false, //如果要代理 websockets，配置这个参数
                pathRewrite: {
                    // 路径改写规则
                    "^/builder": "", // 以/proxy/为开头的改写为''
                },
            },
        },
    },
};
