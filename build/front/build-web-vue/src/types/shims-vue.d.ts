// 有时 TypeScript 无法直接识别 .mjs 文件格式。你可以创建一个 shims-vue.d.ts 文件来告诉 TypeScript 如何处理 Vue 相关的类型声明。
declare module "*.vue" {
	import type { DefineComponent } from "vue";
	const component: DefineComponent<{}, {}, any>;
	export default component;
}
