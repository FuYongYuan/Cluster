import router from "@src/router/config.ts";
import {
	LocalStorageAccountMenuList,
	LocalStorageInvalidDate,
	LocalStorageJwtToken,
} from "@src/apis/commons/constant";
import type {
	NavigationGuardNext,
	RouteLocationNormalized,
	RouteLocationNormalizedLoaded,
} from "vue-router";

router.beforeEach(
	async (
		to: RouteLocationNormalized,
		from: RouteLocationNormalizedLoaded,
		next: NavigationGuardNext,
	) => {
		// 排除不需要权限检查的路径
		const excludedPaths = [
			"/login",
			"/register",
			"/recover",
			"/error/403",
			"/error/404",
			"/error/500",
		];
		if (excludedPaths.includes(to.path)) {
			return next();
		}

		// 检查 JWT Token 和有效期
		const jwtToken = localStorage.getItem(LocalStorageJwtToken);
		const invalidDate = localStorage.getItem(LocalStorageInvalidDate);
		const invalidDateTime = invalidDate ? new Date(invalidDate).getTime() : 0;
		const currentDate = new Date().getTime();

		// 如果没有 Token 或者 Token 过期，跳转到登录页
		if (!jwtToken || !invalidDateTime || invalidDateTime < currentDate) {
			return next("/login");
		}

		if (from.path !== to.path) {
			// 如果是访问 /home 直接跳过
			if (to.path === "/home") {
				return next();
			}

			// 检查菜单权限
			const menuCache = localStorage.getItem(LocalStorageAccountMenuList);
			const menuList = menuCache ? JSON.parse(menuCache) : undefined;

			if (menuList) {
				if (menuScan(menuList, to.path)) {
					return next();
				}
				return next("/error/403");
			}
			return next("/home");
		} else {
			// 如果 from.path 和 to.path 相同，直接调用 next
			return next();
		}
	},
);

/**
 * 检查菜单权限
 * @param menuList 菜单列表
 * @param path 当前路由路径
 * @returns 是否有权限访问
 */
function menuScan(menuList: any[], path: string): boolean {
	let isAuthorized = false;
	for (const menu of menuList) {
		if (menu && menu.isTurn === 1) {
			if (path === menu.menuUrl) {
				isAuthorized = true;
				break;
			}
		}

		if (menu.sub && menu.sub.length > 0) {
			if (menuScan(menu.sub, path)) {
				isAuthorized = true;
				break;
			}
		}
	}
	return isAuthorized;
}
