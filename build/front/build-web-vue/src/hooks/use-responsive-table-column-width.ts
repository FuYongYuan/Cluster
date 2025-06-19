import { computed, ref, onMounted, onUnmounted } from "vue";

/**
 * 响应式列宽 Hook
 *
 * 支持使用百分比字符串（如 "20%"）定义列宽，并动态转换为像素值以兼容 Ant Design Vue 的 <a-table> 组件。
 * 特别适用于需要启用 resizable: true 的场景，特别是搭配百分比时会报错，确保列宽类型正确。
 *
 * @param initialColumns 初始列配置数组，允许使用百分比字符串作为 width 值
 * @param containerSelector 容器选择器，默认为 '.search-result'
 */
export default function useResponsiveTableColumnWidth(
	initialColumns: any[],
	containerSelector = ".search-result",
) {
	// 当前容器宽度（响应式）
	const containerWidth = ref(window.innerWidth);

	// 更新容器宽度
	const updateWidth = () => {
		const container = document.querySelector(containerSelector);
		if (container) {
			containerWidth.value = container.clientWidth;
		}
	};

	// 组件挂载时绑定 resize 监听器
	onMounted(() => {
		updateWidth();
		window.addEventListener("resize", updateWidth);
	});

	// 组件卸载时移除监听器
	onUnmounted(() => {
		window.removeEventListener("resize", updateWidth);
	});

	// 动态计算列宽
	const computedColumns = computed(() => {
		return initialColumns.map((col) => {
			// 如果 width 是百分比字符串，则按比例计算像素值
			if (typeof col.width === "string" && col.width.endsWith("%")) {
				const percentage = parseFloat(col.width) / 100;
				return { ...col, width: Math.floor(containerWidth.value * percentage) };
			}
			return col;
		});
	});

	return {
		/**
		 * 计算后的列配置，每个列的 width 属性均为 Number 类型（单位 px）
		 */
		computedColumns,
	};
}
