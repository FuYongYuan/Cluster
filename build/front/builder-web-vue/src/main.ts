import {createApp} from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
// 引入拦截器
import "./interceptor";
// Antd组件引入
import {components} from "./antd";

// 创建APP
const app = createApp(App);

// 循环按需引入Antd组件
components.forEach((component) => {
    app.use(component);
});

// vuex共享状态管理
app.use(store);

// 路由
app.use(router);

// 手动挂载 #app = index.html 中div的id
app.mount("#app");
