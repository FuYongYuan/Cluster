import {
	createRouter,
	createWebHistory,
	type RouteRecordRaw,
} from "vue-router";
import Layout from "@src/components/layout/Layout.vue";

const routes: Array<RouteRecordRaw> = [
	{
		path: "/login",
		name: "Login",
		component: () => import("@src/views/system/Login.vue"),
		meta: {
			menuName: "登录",
		},
	},
	{
		path: "/register",
		name: "Register",
		component: () => import("@src/views/system/Register.vue"),
		meta: {
			menuName: "注册",
		},
	},
	{
		path: "/recover",
		name: "Recover",
		component: () => import("@src/views/system/Recover.vue"),
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
				component: () => import("@src/views/Home.vue"),
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
				component: () => import("@src/views/menu/Manage.vue"),
				meta: {
					menuId: 10101,
					menuName: "菜单管理",
					keepAlive: true,
				},
			},
			{
				path: "/role/manage",
				name: "RoleManage",
				component: () => import("@src/views/role/Manage.vue"),
				meta: {
					menuId: 10102,
					menuName: "角色管理",
					keepAlive: true,
				},
			},
			{
				path: "/manager/manage",
				name: "ManagerManage",
				component: () => import("@src/views/manager/Manage.vue"),
				meta: {
					menuId: 10103,
					menuName: "管理员管理",
					keepAlive: true,
				},
			},
			{
				path: "/parameter/manage",
				name: "ParameterManage",
				component: () => import("@src/views/parameter/Manage.vue"),
				meta: {
					menuId: 10104,
					menuName: "参数管理",
					keepAlive: true,
				},
			},
			{
				path: "/group/manage",
				name: "GroupManage",
				component: () => import("@src/views/group/Manage.vue"),
				meta: {
					menuId: 10201,
					menuName: "项目群管理",
					keepAlive: true,
				},
			},
			{
				path: "/group/detail",
				name: "GroupDetail",
				component: () => import("@src/views/group/Detail.vue"),
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
				component: () => import("@src/components/error/403.vue"),
				meta: {
					menuId: 403,
					menuName: "403错误",
				},
			},
			{
				path: "404",
				name: "404",
				component: () => import("@src/components/error/404.vue"),
				meta: {
					menuId: 404,
					menuName: "404错误",
				},
			},
			{
				path: "500",
				name: "500",
				component: () => import("@src/components/error/500.vue"),
				meta: {
					menuId: 500,
					menuName: "500错误",
				},
			},
		],
	},
];

const router = createRouter({
	history: createWebHistory(import.meta.env.BASE_URL),
	routes,
});

export default router;
