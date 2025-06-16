<template>
    <a-modal
            :open="open"
            :confirm-loading="loadingState"
            @cancel="handleCancel"
    >
        <template #title>
            <icon-font type="icon-detail"/>
            菜单详情
        </template>
        <template #footer>
            <a-button class="ant-btn-daybreak" :loading="loadingState" @click="handleOk">
                <icon-font type="icon-save" v-if="!loadingState"/>
                保存
            </a-button>
            <a-button :loading="loadingState" @click="handleCancel">
                <icon-font type="icon-close" v-if="!loadingState"/>
                关闭
            </a-button>
        </template>

        <a-spin tip="加载中......" :spinning="loadingState">
            <a-form
                    ref="detail"
                    :model="form"
                    :rules="rules"
                    :label-col="{ span: 4 }"
                    :wrapper-col="{ span: 20 }"
            >
                <a-form-item has-feedback label="菜单名称" name="menuName">
                    <a-input
                            v-model:value="form.menuName"
                            placeholder="菜单名称"
                            allowClear
                    >
                        <template v-slot:prefix>
                            <icon-font type="icon-menu"/>
                        </template>
                    </a-input>
                </a-form-item>
                <a-form-item has-feedback label="菜单路径" name="menuUrl">
                    <a-input
                            v-model:value="form.menuUrl"
                            placeholder="菜单路径"
                            allowClear
                    >
                        <template v-slot:prefix>
                            <icon-font type="icon-menu"/>
                        </template>
                    </a-input>
                </a-form-item>
                <a-form-item has-feedback label="菜单图标" name="menuIcon">
                    <a-input
                            v-model:value="form.menuName"
                            placeholder="菜单图标"
                            allowClear
                    >
                        <template v-slot:prefix>
                            <icon-font type="icon-menu"/>
                        </template>
                    </a-input>
                </a-form-item>
                <a-form-item has-feedback label="页面名称" name="pageName">
                    <a-input
                            v-model:value="form.pageName"
                            placeholder="页面名称"
                            allowClear
                    >
                        <template v-slot:prefix>
                            <icon-font type="icon-menu"/>
                        </template>
                    </a-input>
                </a-form-item>
                <a-form-item has-feedback label="排序" name="menuOrder">
                    <a-input
                            v-model:value="form.menuOrder"
                            placeholder="排序"
                            type="number"
                            allowClear
                    >
                        <template v-slot:prefix>
                            <icon-font type="icon-Field-Binary"/>
                        </template>
                    </a-input>
                </a-form-item>
                <a-form-item has-feedback label="是否跳转" name="isTurn">
                    <a-radio-group v-model:value="form.isTurn" button-style="solid">
                        <a-radio-button :value=0>否</a-radio-button>
                        <a-radio-button :value=1>是</a-radio-button>
                    </a-radio-group>
                </a-form-item>
                <a-form-item has-feedback label="是否主页" name="isHome">
                    <a-radio-group v-model:value="form.isHome" button-style="solid">
                        <a-radio-button :value=0>否</a-radio-button>
                        <a-radio-button :value=1>是</a-radio-button>
                    </a-radio-group>
                </a-form-item>
                <a-form-item has-feedback label="父级菜单" name="parentId">
                    <a-select v-model:value="form.parentId" :options="menuAll">
                    </a-select>
                </a-form-item>
                <a-form-item label="备注" name="remark">
                    <a-textarea
                            v-model:value="form.remark"
                            placeholder="备注"
                            :maxlength="1000"
                            allowClear
                            showCount
                    />
                </a-form-item>
            </a-form>
        </a-spin>
    </a-modal>
</template>

<script lang="ts">
import { defineComponent, onMounted, reactive, ref, toRefs } from "vue";
import { FormInstance, message } from "ant-design-vue";
import { RuleObject } from "ant-design-vue/es/form";
import IconFont from "@src/assets/iconfont/icon";
import {
	getMenu,
	queryMenuAllParent,
	saveMenu,
} from "@src/apis/capability/service";
import type { MenuDTO } from "@src/apis/capability/dto";

export default defineComponent({
	// 页面名称
	name: "MenuDetail",
	// 组件
	components: {
		// 图标库
		IconFont,
	},
	// 父组件传递值
	props: {
		// 是否显示
		open: {
			type: Boolean,
			default: false,
		},
	},
	// 发出更新
	emits: ["update:open", "query"],
	// 执行
	setup(props, context) {
		//------------------------------------------------------------------------------------------------------------------初试事件
		// 初始化加载执行
		onMounted(() => {
			loadingParentMenu();
		});
		//------------------------------------------------------------------------------------------------------------------参数
		// 详情DOM
		const detail = ref<FormInstance>();

		// 详情页面数据
		const detailData = reactive({
			// 表单
			form: ref<MenuDTO>({
				id: -1,
			}),
			// 父级菜单列表
			menuAll: ref<any[]>([]),

			loadingState: false,
		});

		//------------------------------------------------------------------------------------------------------------------方法
		// 关闭方法
		const handleCancel = () => {
			// 关闭显示
			context.emit("update:open", false);
			// 清理表单
			detailData.form = {
				id: -1,
			};

			detail.value?.resetFields();
		};

		// 提交方法
		const handleOk = async () => {
			detail.value
				?.validate()
				.then(async () => {
					// 开始
					detailData.loadingState = true;

					// 保存
					const result = await saveMenu(detailData.form);
					// 保存结果
					message.success(result);
					// 触发查询
					context.emit("query");

					// 结束
					detailData.loadingState = false;

					//关闭
					handleCancel();
				})
				.catch(() => {
					// 保存结果
					message.warn("请填写必要信息！");
				});
		};

		// 根据Id查询
		const getById = async (searchId: number) => {
			if (searchId > 0) {
				// 开始
				detailData.loadingState = true;

				// 查询
				const result = await getMenu(searchId);
				if (result !== undefined) {
					detailData.form = result;
				}

				// 结束
				detailData.loadingState = false;
			}
		};

		// 查询所有菜单
		const loadingParentMenu = async () => {
			// 开始
			detailData.loadingState = true;

			const data = ref<MenuDTO[]>([]);

			// 查询
			const result = await queryMenuAllParent();
			if (result !== undefined) {
				data.value = result;
			}

			// 父级菜单
			for (let i = 0; i < data.value.length; i++) {
				detailData.menuAll.push({
					value: data.value[i].id,
					label: data.value[i].menuName,
				});
			}

			// 结束
			detailData.loadingState = false;
		};

		//------------------------------------------------------------------------------------------------------------------验证
		// 校验规则
		const rules = {
			menuName: [
				{
					required: true,
					validator: async (rule: RuleObject, value: string | undefined) => {
						if (value === undefined || value === "") {
							return Promise.reject("请输入菜单名称！");
						} else {
							return Promise.resolve();
						}
					},
					trigger: "change",
				},
			],
			menuUrl: [
				{
					required: true,
					validator: async (rule: RuleObject, value: string | undefined) => {
						if (value === undefined || value === "") {
							return Promise.reject("请输入菜单URL！");
						} else {
							return Promise.resolve();
						}
					},
					trigger: "change",
				},
			],
			menuIcon: [
				{
					required: true,
					validator: async (rule: RuleObject, value: string | undefined) => {
						if (value === undefined || value === "") {
							return Promise.reject("请输入菜单图标！");
						} else {
							return Promise.resolve();
						}
					},
					trigger: "change",
				},
			],
			pageName: [
				{
					required: true,
					validator: async (rule: RuleObject, value: string | undefined) => {
						if (value === undefined || value === "") {
							return Promise.reject("请输入页面名称！");
						} else {
							return Promise.resolve();
						}
					},
					trigger: "change",
				},
			],
			isTurn: [
				{
					required: true,
					validator: async (rule: RuleObject, value: number | undefined) => {
						if (value === undefined) {
							return Promise.reject("请选择是否跳转！");
						} else {
							return Promise.resolve();
						}
					},
					trigger: "change",
				},
			],
			isHome: [
				{
					required: true,
					validator: async (rule: RuleObject, value: number | undefined) => {
						if (value === undefined) {
							return Promise.reject("请选择是否首页！");
						} else {
							return Promise.resolve();
						}
					},
					trigger: "change",
				},
			],
			menuOrder: [
				{
					required: true,
					validator: async (rule: RuleObject, value: number | undefined) => {
						if (value === undefined) {
							return Promise.reject("请输入菜单排序！");
						} else {
							return Promise.resolve();
						}
					},
					trigger: "change",
				},
			],
		};

		//------------------------------------------------------------------------------------------------------------------返回
		return {
			...toRefs(detailData),
			detail,
			rules,
			getById,
			handleCancel,
			handleOk,
		};
	},
});
</script>

<style scoped>
@import "@src/assets/css/button.css";

</style>

