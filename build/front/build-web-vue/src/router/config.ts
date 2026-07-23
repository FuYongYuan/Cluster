import ManageLayout from "@src/components/layout/ManageLayout.vue";
import {
	createRouter,
	createWebHistory,
	type RouteRecordRaw,
} from "vue-router";

const routes: Array<RouteRecordRaw> = [
	{
		path: "/login",
		name: "ManagerLogin",
		component: () => import("@src/views/system/ManagerLogin.vue"),
		meta: {
			menuName: "登录",
		},
	},
	{
		path: "/register",
		name: "ManagerRegister",
		component: () => import("@src/views/system/ManagerRegister.vue"),
		meta: {
			menuName: "注册",
		},
	},
	{
		path: "/recover",
		name: "ManagerRecover",
		component: () => import("@src/views/system/ManagerRecover.vue"),
		meta: {
			menuName: "找回密码",
		},
	},
	{
		path: "/",
		name: "ManageLayout",
		component: ManageLayout,
		meta: {
			menuName: "布局",
		},
		children: [
			{
				path: "/home",
				name: "ManageHome",
				component: () => import("@src/views/ManageHome.vue"),
				meta: {
					menuId: 6508370886732800,
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
					menuId: 6508370886732803,
					menuName: "菜单管理",
					keepAlive: true,
				},
			},
			{
				path: "/role/manage",
				name: "RoleManage",
				component: () => import("@src/views/role/Manage.vue"),
				meta: {
					menuId: 6508370886732804,
					menuName: "角色管理",
					keepAlive: true,
				},
			},
			{
				path: "/manager/manage",
				name: "ManagerManage",
				component: () => import("@src/views/manager/Manage.vue"),
				meta: {
					menuId: 6508370886732805,
					menuName: "管理员管理",
					keepAlive: true,
				},
			},
			{
				path: "/parameter/manage",
				name: "ParameterManage",
				component: () => import("@src/views/parameter/Manage.vue"),
				meta: {
					menuId: 6508370886732806,
					menuName: "参数管理",
					keepAlive: true,
				},
			},
			{
				path: "/group/manage",
				name: "GroupManage",
				component: () => import("@src/views/group/Manage.vue"),
				meta: {
					menuId: 6508370886732807,
					menuName: "项目群管理",
					keepAlive: true,
				},
			},
			{
				path: "/group/detail",
				name: "GroupDetail",
				component: () => import("@src/views/group/Detail.vue"),
				meta: {
					menuId: 6508370886732808,
					menuName: "新增项目群",
					keepAlive: true,
				},
			},
		],
	},
	{
		path: "/error",
		name: "Error",
		component: ManageLayout,
		redirect: "/error/404",
		meta: {
			menuName: "错误",
		},
		children: [
			{
				path: "404",
				name: "404",
				component: () => import("@src/components/error/404.vue"),
				meta: {
					menuId: 404,
					menuName: "404错误",
				},
			},
		],
	},
	{
		path: "/error/404",
		name: "Error404Standalone",
		component: () => import("@src/components/error/404.vue"),
		meta: {
			menuName: "404错误",
		},
	},
	{
		path: "/:pathMatch(.*)*",
		name: "NotFound",
		redirect: "/error/404",
	},
];

const router = createRouter({
	history: createWebHistory(import.meta.env.BASE_URL),
	routes,
});

export default router;
