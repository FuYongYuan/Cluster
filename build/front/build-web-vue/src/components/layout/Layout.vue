<template>
  <a-config-provider :locale="locale">
    <a-layout>
      <MenuLayout/>
      <a-layout>
        <HeaderLayout/>
        <ContentLayout/>
        <FooterLayout/>
      </a-layout>
    </a-layout>
  </a-config-provider>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import MenuLayout from "@src/components/layout/menu/MenuLayout.vue";
import HeaderLayout from "@src/components/layout/header/HeaderLayout.vue";
import FooterLayout from "@src/components/layout/footer/FooterLayout.vue";
import ContentLayout from "@src/components/layout/content/ContentLayout.vue";
import zhCN from "ant-design-vue/es/locale/zh_CN";
import dayjs from "dayjs";
import "dayjs/locale/zh-cn";

dayjs.locale("zh-cn");

export default defineComponent({
	name: "Layout",
	components: {
		MenuLayout,
		HeaderLayout,
		FooterLayout,
		ContentLayout,
	},
	// 数据
	data() {
		return {
			// 表格语种
			locale: zhCN,
		};
	},
	setup() {
		//------------------------------------------------------------------------------------------------------------------参数

		//------------------------------------------------------------------------------------------------------------------返回
		return {};
	},
});
</script>
