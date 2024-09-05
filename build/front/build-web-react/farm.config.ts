import { defineConfig } from '@farmfe/core';

export default defineConfig({
  // 编译相关配置
  compilation: {
    input: {
      // 可以配置相对或者绝对路径
      index: "./public/index.html",
    }
  },
  // Dev Server 相关配置
  server: {
    // 端口
    port: 9000,
    // 热模块替换-生产环境防止被F12调试需要关闭
    hmr: true
  },
  // 插件配置
  plugins: [
      '@farmfe/plugin-react'
  ]
});
