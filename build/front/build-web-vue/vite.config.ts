import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import {AndDesignVueResolve, createStyleImportPlugin} from 'vite-plugin-style-import'

// https://vite.dev/config/ 官网文档地址
export default defineConfig({
    plugins: [
        // 引入 vue 插件
        vue(),
        // 使用 createStyleImportPlugin 来创建 按需引入插件
        createStyleImportPlugin({
            libs: [
                // 使用 Ant Design Vue 的默认配置
                AndDesignVueResolve(),
            ],
        }),
    ],
    // 简化引用-仅在【模块解析（打包和开发运行）】【影响运行时】
    resolve: {
        alias: {
            // 同步 tsconfig.json 的路径别名
            '@src': '/src',
        },
    },
    server: {
        // 指定端口
        port: 5173,
        // 自动打开浏览器
        open: false,
        // 如果端口被占用，报错而不是自动尝试其他端口
        strictPort: true,
        watch: {
            // 避免某些文件系统变动无法被检测到的问题
            usePolling: true,
        },
        proxy: {
            // api 代理
            "/api": {
                // 后台接口域名
                target: "http://localhost:27000/",
                // false为http访问，true为https访问
                secure: false,
                // 跨域访问设置，true代表跨域
                changeOrigin: true,
                //如果要代理 websockets，配置这个参数
                ws: false,
                // 路径改写规则 以/proxy/为开头的改写为''
                rewrite: (path) => path.replace(/^\/api/, ""),
            },
        },
    },

})
