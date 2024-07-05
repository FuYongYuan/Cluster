import {createRouter, createWebHistory, RouteRecordRaw} from "vue-router";
import Layout from "@/components/layout/Layout.vue";

const routes: Array<RouteRecordRaw> = [
    {
        path: "/login",
        name: "Login",
        component: () => import("@/views/system/Login.vue"),
        meta: {
            menuName: "登录",
        },
    },
    {
        path: "/register",
        name: "Register",
        component: () => import("@/views/system/Register.vue"),
        meta: {
            menuName: "注册",
        },
    },
    {
        path: "/recover",
        name: "Recover",
        component: () => import("@/views/system/Recover.vue"),
        meta: {
            menuName: "找回密码",
        },
    },
    {
        path: "/",
        name: "Layout",
        component: Layout,
        meta: {
            menuName: "布局",
        },
        children: [
            {
                path: "/home",
                name: "Home",
                component: () => import("@/views/Home.vue"),
                meta: {
                    menuId: 100,
                    menuName: "首页",
                    keepAlive: true,
                },
            },
            //----------------------------------------------------------------------------------------------------------系统菜单
            {
                path: "/menu/manage",
                name: "MenuManage",
                component: () => import("@/views/menu/Manage.vue"),
                meta: {
                    menuId: 10101,
                    menuName: "菜单管理",
                    keepAlive: true,
                },
            },
            {
                path: "/role/manage",
                name: "RoleManage",
                component: () => import("@/views/role/Manage.vue"),
                meta: {
                    menuId: 10102,
                    menuName: "角色管理",
                    keepAlive: true,
                },
            },
            {
                path: "/manager/manage",
                name: "ManagerManage",
                component: () => import("@/views/manager/Manage.vue"),
                meta: {
                    menuId: 10103,
                    menuName: "管理员管理",
                    keepAlive: true,
                },
            },
            {
                path: "/parameter/manage",
                name: "ParameterManage",
                component: () => import("@/views/parameter/Manage.vue"),
                meta: {
                    menuId: 10104,
                    menuName: "参数管理",
                    keepAlive: true,
                },
            },
            {
                path: "/group/manage",
                name: "GroupManage",
                component: () => import("@/views/group/Manage.vue"),
                meta: {
                    menuId: 10201,
                    menuName: "项目群管理",
                    keepAlive: true,
                },
            },
            {
                path: "/group/detail",
                name: "GroupDetail",
                component: () => import("@/views/group/Detail.vue"),
                meta: {
                    menuId: 10202,
                    menuName: "新增项目群",
                    keepAlive: true,
                },
            },
        ],
    },
    {
        path: "/error",
        name: "Error",
        component: Layout,
        redirect: "/error/404",
        meta: {
            menuName: "错误",
        },
        children: [
            {
                path: "403",
                name: "403",
                component: () => import("@/components/error/403.vue"),
                meta: {
                    menuId: 403,
                    menuName: "403错误",
                },
            },
            {
                path: "404",
                name: "404",
                component: () => import("@/components/error/404.vue"),
                meta: {
                    menuId: 404,
                    menuName: "404错误",
                },
            },
            {
                path: "500",
                name: "500",
                component: () => import("@/components/error/500.vue"),
                meta: {
                    menuId: 500,
                    menuName: "500错误",
                },
            },
        ],
    },
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
});

export default router;
