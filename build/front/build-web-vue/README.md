# 引用说明

> 注意启动可以使用dev启动，但调试需要使用idea建的调试，才能在开发者模式下刷新。默认浏览器等打开是无法在F12开发者模式下刷新，但可以正常使用仅无法调试

## 引用说明

### dependencies

- 图标
    - "@ant-design/icons-vue"
- CSS-in-JS
    - "@vue-styled-components/core"
- antd
    - "ant-design-vue"
- 请求用
    - "axios"
- 日志打印 vite按需引用需要
    - "consola"
- 国际化
    - "dayjs"
- vue状态管理
    - "pinia"
- skywalking 链路跟踪
    - "skywalking-backend-js": "^0.8.0"
- md5加密
    - "ts-md5"
- vue
    - "vue"
- 路由器
    - "vue-router"

### devDependencies

- 代码检查和格式化
    - "@biomejs/biome"
- vite构建 vue插件
    - "@vitejs/plugin-vue"
- ts
    - "typescript"
- vite构建
    - "vite"
- vite按需引用
    - "vite-plugin-style-import"
- vue ts支持插件
    - "vue-tsc"

---

## 更新

已安装全局更新工具，使用ncu可以检查更新，使用 ncu -u 可以自动更新版本

### 安装全局更新工具

> npm install -g npm-check-updates

### 更新各类包

> ncu -u

安装完需要
> npm install

## 格式化和安全检查

### 格式化文件

> npx @biomejs/biome format --write ./src

### Lint 文件

> npx @biomejs/biome lint ./src

### 运行格式化，Lint 等，并应用安全的建议

> npx @biomejs/biome check --write ./src

### 在 CI 环境中检查所有文件是否符合格式，Lint 等

> npx @biomejs/biome ci ./src

## 清理缓存

### 删除node模块

> rm -rf node_modules

### 清理缓存

> npm cache clean --force

### 重新安装

> npm install
