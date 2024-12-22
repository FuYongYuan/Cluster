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
})
