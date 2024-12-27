<template>
  <a-layout-content class="app-content">
    <a-tabs
        v-model:activeKey="activeKey"
        hide-add
        type="editable-card"
        @change="changeTab"
        @edit="removeTab"
    >
      <a-tab-pane
          :key="homeId"
          :tab="homeMenuName"
          :closable="false"
      ></a-tab-pane>
      <a-tab-pane
          v-for="visitedRoute in visitedRoutes"
          :key="visitedRoute.id"
          :tab="visitedRoute.menuName"
      ></a-tab-pane>
    </a-tabs>
    <router-view v-slot="{ Component }">
      <keep-alive :include="include">
        <component :is="Component"/>
      </keep-alive>
    </router-view>
  </a-layout-content>
</template>

<script lang="ts">
import { defineComponent, onMounted, reactive, toRefs, watch } from "vue";
import { useRoute, useRouter } from "vue-router";
import { LocalStorageAccountMenuList } from "@src/apis/commons/constant";

/**
 * 内容数据类型
 */
type ContentDataType = {
	// tab页签选中
	activeKey: number;
	// 已开启页签路由集合
	visitedRoutes: VisitedRouteType[];
	// 首页的id
	homeId: number;
	// 首页的Key
	homeUrl: string;
	// 首页Tab
	homeMenuName: string;
	// 首页页面名称
	homePageName: string;
	// 缓存组件defineComponent name名称
	include: string[];
};

/**
 * 已访问路由数据类型
 */
type VisitedRouteType = {
	// Id
	id: number;
	// 标题
	menuName: string;
	// 页面名称
	pageName: string;
	// 全路径
	fullPath: string;
};

// 执行内容
export default defineComponent({
	// 页面名称
	name: "ContentLayout",
	// 执行
	setup() {
		//------------------------------------------------------------------------------------------------------------------参数
		// 获取路由
		const router = useRouter();
		const route = useRoute();
		// 内容数据
		const contentData = reactive<ContentDataType>({
			activeKey: 0,
			visitedRoutes: [],
			homeId: 0,
			homeUrl: "",
			homeMenuName: "",
			homePageName: "",
			include: [],
		});
		// 菜单列表缓存
		const menuCache = localStorage.getItem(LocalStorageAccountMenuList);
		// 菜单列表
		const menuList = menuCache != null ? JSON.parse(menuCache) : undefined;
		// 获取首页
		menuList.forEach((menu: any) => {
			if (menu.isHome === 1) {
				contentData.activeKey = menu.id;
				contentData.homeId = menu.id;
				contentData.homeUrl = menu.menuUrl;
				contentData.homeMenuName = menu.menuName;
				contentData.homePageName = menu.pageName;
			}
		});

		//------------------------------------------------------------------------------------------------------------------方法
		// 加载完成触发事件加载首页
		onMounted(async () => {
			changeTab(contentData.homeId);
			// 写入缓存
			const visitedRoute = {
				id: contentData.homeId,
				menuName: contentData.homeMenuName,
				pageName: contentData.homePageName,
				fullPath: contentData.homeUrl,
			};
			isKeepAlive(true, visitedRoute);
		});

		// 切换tab
		const changeTab = (id: number) => {
			contentData.activeKey = id;
			if (contentData.activeKey === contentData.homeId) {
				router.push(contentData.homeUrl);
			} else {
				const routeItem = contentData.visitedRoutes.find(
					(routeItem) => routeItem.id === id,
				);
				if (routeItem) {
					router.push(routeItem.fullPath);
				}
			}
		};

		// 菜单切换监听
		watch(
			() => route.fullPath,
			(to) => {
				if (contentData.homeUrl !== to) {
					const routeItem = contentData.visitedRoutes.find(
						(routeItem) => routeItem.fullPath === to,
					);
					if (!routeItem) {
						const id: any = route.meta.menuId;
						const menuName: any = route.meta.menuName;
						const pageName: any = route.name;
						const fullPath = route.fullPath;
						const visitedRoute = { id, menuName, pageName, fullPath };
						contentData.visitedRoutes.push(visitedRoute);
						// 缓存
						const keepAlive: any = route.meta.keepAlive;
						isKeepAlive(keepAlive, visitedRoute);
						// 转向ID
						contentData.activeKey = id;
					} else {
						contentData.activeKey = routeItem.id;
					}
				} else {
					contentData.activeKey = contentData.homeId;
				}
			},
		);

		// 删除tab
		const removeTab = (id: number) => {
			const visitedRoute = contentData.visitedRoutes.find(
				(routeItem) => id === routeItem.id,
			);
			if (visitedRoute) {
				contentData.visitedRoutes.forEach((routeItem, index) => {
					if (routeItem.fullPath === visitedRoute.fullPath) {
						contentData.visitedRoutes.splice(index, 1);
						// 删除后切换 删除的是当前激活的Tab则进行选择新页签
						if (contentData.activeKey === visitedRoute.id) {
							// 关闭最后一页-显示删除后的最后一页
							if (
								contentData.visitedRoutes.length <= index &&
								contentData.visitedRoutes.length > 0
							) {
								changeTab(
									contentData.visitedRoutes[
										contentData.visitedRoutes.length - 1
									].id,
								);
							}
							// 没有页签时候显示首页
							else if (contentData.visitedRoutes.length === 0) {
								changeTab(contentData.homeId);
							}
							// 是当前页的情况下并且不是最后一页时将页面切换到下一页
							else {
								changeTab(contentData.visitedRoutes[index].id);
							}
						}
					}
				});

				// 缓存
				isKeepAlive(false, visitedRoute);
			}
		};

		// 动态填写缓存
		const isKeepAlive = (
			keepAlive: boolean,
			visitedRoute: VisitedRouteType,
		) => {
			if (keepAlive) {
				if (
					visitedRoute.pageName != undefined &&
					visitedRoute.pageName !== ""
				) {
					if (
						!contentData.include.find(
							(menuName) => menuName === visitedRoute.pageName,
						)
					) {
						contentData.include.push(visitedRoute.pageName);
					}
				}
			} else {
				if (
					visitedRoute.pageName != undefined &&
					visitedRoute.pageName !== ""
				) {
					if (
						contentData.include.find(
							(menuName) => menuName === visitedRoute.pageName,
						)
					) {
						contentData.include.splice(
							contentData.include.indexOf(visitedRoute.pageName),
							1,
						);
					}
				}
			}
		};

		//------------------------------------------------------------------------------------------------------------------返回
		return {
			...toRefs(contentData),
			changeTab,
			removeTab,
		};
	},
});
</script>

<style scoped>
.app-content {
  margin: 5px 5px 5px 5px;
  background-color: #fff;
  border-radius: 5px;
}

/* 使用 :deep() 来作用于子组件中的类 */
:deep(.ant-tabs-top > .ant-tabs-nav,
      .ant-tabs-bottom > .ant-tabs-nav,
      .ant-tabs-top > div > .ant-tabs-nav,
      .ant-tabs-bottom > div > .ant-tabs-nav) {
  margin: 0 0 5px 0;
}
</style>