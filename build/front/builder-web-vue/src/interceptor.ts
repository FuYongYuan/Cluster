import router from "@/router";
import {LocalStorageAccountMenuList, LocalStorageInvalidDate, LocalStorageJwtToken} from "@/apis/commons/constant";
import {NavigationGuardNext, RouteLocationNormalized} from "vue-router";

router.beforeEach(
    async (
        to: RouteLocationNormalized,
        from: RouteLocationNormalized,
        next: NavigationGuardNext
    ) => {
        if (
            to.path === "/login" ||
            to.path === "/register" ||
            to.path === "/recover" ||
            to.path === "/error/403" ||
            to.path === "/error/404" ||
            to.path === "/error/500"
        ) {
            next();
        } else {
            const jwtToken = localStorage.getItem(LocalStorageJwtToken);
            const invalidDate = localStorage.getItem(LocalStorageInvalidDate);
            let invalidDateTime: number;
            if (invalidDate !== null) {
                invalidDateTime = new Date(invalidDate).getTime();
            } else {
                invalidDateTime = 0;
            }
            const currentDate = new Date();
            if (!jwtToken || (!invalidDateTime || invalidDateTime == 0 || (invalidDateTime < currentDate.getTime()))) {
                next("/login");
            } else {
                if (to.path === "/home") {
                    next();
                } else {
                    const menuCache = localStorage.getItem(LocalStorageAccountMenuList);
                    const menuList =
                        menuCache !== undefined &&
                        menuCache !== null &&
                        menuCache !== "undefined" &&
                        menuCache !== "null" ?
                            JSON.parse(menuCache) : undefined
                    ;
                    if (menuList) {
                        if (menuScan(menuList, to.path)) {
                            next();
                        } else {
                            next("/error/403");
                        }
                    } else {
                        next("/home");
                    }
                }
            }
        }
    }
);

/**
 * 循环判断是否有权限访问菜单
 * @param menuList 菜单列表
 * @param path URL地址
 */
function menuScan(
    menuList: any,
    path: string
): boolean {
    let isGo = false;
    for (let i = 0; i < menuList.length; i++) {
        if (menuList[i]) {
            if (menuList[i].isTurn === 1) {
                if (path === menuList[i].menuUrl) {
                    isGo = true;
                }
            }
            if (menuList[i].sub.length > 0) {
                if (!isGo) {
                    isGo = menuScan(menuList[i].sub, path);
                }
            }
        }
    }
    return isGo;
}