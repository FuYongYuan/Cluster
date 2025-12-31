import { createApp } from "vue";
import App from "./App.vue";
// 引入路由
import router from "./router/config.ts";
// 引入状态管理
import { createPinia } from "pinia";
// 引入拦截器
import "./router/interceptor.ts";
// Antd组件引入
import { components } from "./antd";

// 创建APP
const app = createApp(App);

// 循环按需引入Antd组件
components.forEach((component) => {
	app.use(component);
});

// pinia共享状态管理
app.use(createPinia());

// 路由
app.use(router);

// 手动挂载 #app = index.html 中div的id
app.mount("#app");

// skywalking
import skywalking from "skywalking-client-js";
import pkg from "../package.json";

skywalking.ClientMonitor.register({
	service: pkg.name + "-skywalking",
	serviceVersion: pkg.version,
	pagePath: location.href,
	collector: "/skywalking",
	jsErrors: true,
	apiErrors: true,
	resourceErrors: true,
	useFmp: true,
	enableSPA: true,
});
