<template>
    <a-layout-sider
            style="min-height: 100vh"
            v-model:collapsed="collapsed"
            collapsible
    >
        <Logo/>
        <a-menu
                theme="dark"
                v-model:selectedKeys="selectedKeys"
                mode="inline"
        >
            <!-- 动态菜单 -->
            <template v-for="menu in menuList">
                <!-- 存在子菜单 -->
                <a-sub-menu v-if="menu.sub && menu.sub.length > 1" :key="menu.id">
                    <template v-slot:title>
            <span>
              <icon-font :type="menu.menuIcon"/>
              <span>{{ menu.menuName }}</span>
            </span>
                    </template>
                    <!-- 子菜单 -->
                    <a-menu-item v-for="sub in menu.sub" :key="sub.id"
                                 @click="clickMenuItem(sub.id,sub.pageName,sub.menuUrl,sub.isTurn)">
                        <icon-font :type="sub.menuIcon"/>
                        <span>{{ sub.menuName }}</span>
                    </a-menu-item>
                </a-sub-menu>
                <!-- 单独菜单 -->
                <a-menu-item v-else :key="menu.id"
                             @click="clickMenuItem(menu.id,menu.pageName,menu.menuUrl,menu.isTurn)">
                    <icon-font :type="menu.menuIcon"/>
                    <span>{{ menu.menuName }}</span>
                </a-menu-item>
            </template>
        </a-menu>
    </a-layout-sider>
</template>

<script lang="ts">
import { defineComponent, ref, watch } from "vue";
import Logo from "@src/components/logo/Logo.vue";
import IconFont from "@src/assets/iconfont/icon";
import { LocalStorageAccountMenuList } from "@src/apis/commons/constant";
import { useRoute, useRouter } from "vue-router";

export default defineComponent({
	// 页面名称
	name: "MenuLayout",
	// 组件
	components: {
		// 图标库
		IconFont,
		// logo
		Logo,
	},
	// 数据
	data() {
		return {
			// 抽屉打开收缩状态
			collapsed: ref<boolean>(false),
		};
	},
	// 执行
	setup() {
		//------------------------------------------------------------------------------------------------------------------参数
		// 获取路由
		const router = useRouter();
		const route = useRoute();
		// 菜单列表缓存
		const menuCache = localStorage.getItem(LocalStorageAccountMenuList);
		// 菜单列表
		const menuList = menuCache != null ? JSON.parse(menuCache) : undefined;
		//选中菜单
		const selectedKeys = ref<number[]>([]);
		// 获取首页
		menuList.forEach((menu: any) => {
			if (menu.isHome === 1) {
				selectedKeys.value = [menu.id];
			}
		});

		//------------------------------------------------------------------------------------------------------------------方法
		// 子菜单-实际跳转菜单-点击事件
		const clickMenuItem = (
			id: number,
			pageName: string,
			menuUrl: string,
			isTurn: boolean,
		) => {
			if (isTurn) {
				if (router.hasRoute(pageName)) {
					router.push({ path: menuUrl });
				} else {
					router.push({ path: "/error/404" });
				}
			}
		};

		// 菜单切换监听
		watch(
			() => route.meta.menuId,
			(menuId) => {
				selectedKeys.value = [menuId];
			},
		);

		//------------------------------------------------------------------------------------------------------------------返回
		return {
			selectedKeys,
			menuList,
			clickMenuItem,
		};
	},
});
</script>
