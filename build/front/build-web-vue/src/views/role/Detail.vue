<template>
    <a-modal
            :open="open"
            :confirm-loading="loadingState"
            @cancel="handleCancel"
    >
        <template #title>
            <icon-font type="icon-detail"/>
            角色详情
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
                <a-form-item has-feedback label="角色名称" name="roleName">
                    <a-input
                            v-model:value="form.roleName"
                            placeholder="角色名称"
                            allowClear
                    >
                        <template v-slot:prefix>
                            <icon-font type="icon-skin"/>
                        </template>
                    </a-input>
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

                <a-form-item has-feedback label="菜单" name="menuIds">
                    <a-transfer
                            v-model:target-keys="transferHave"
                            :data-source="transferData"
                            show-search
                            :filter-option="transferFilterOption"
                            :render="item => item.title"
                            @change="transferHandleChange"
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
import { getRole, saveRole } from "@src/apis/authorization/service";
import { queryMenuAll } from "@src/apis/capability/service";
import type { TransferData } from "@src/apis/commons/dto";
import type { RoleDTO } from "@src/apis/authorization/dto";
import type { MenuDTO } from "@src/apis/capability/dto";

export default defineComponent({
	// 页面名称
	name: "RoleDetail",
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
			transferQueryData();
		});
		//------------------------------------------------------------------------------------------------------------------参数
		// 详情DOM
		const detail = ref<FormInstance>();

		// 详情页面数据
		const detailData = reactive({
			// 表单
			form: ref<RoleDTO>({
				id: -1,
			}),
			// 穿梭框拥有
			transferHave: ref<string[]>([]),
			// 穿梭框数据
			transferData: ref<TransferData[]>([]),

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
			detailData.transferHave = [];

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
					const result = await saveRole(detailData.form);
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
				const result = await getRole(searchId);
				if (result !== undefined) {
					detailData.form = result;
					if (result.menuIds !== undefined) {
						detailData.transferHave = result.menuIds.split(",");
					}
				}

				// 结束
				detailData.loadingState = false;
			}
		};

		// 查询所有菜单
		const transferQueryData = async () => {
			// 开始
			detailData.loadingState = true;

			const data = ref<MenuDTO[]>([]);

			// 查询
			const result = await queryMenuAll();
			if (result !== undefined) {
				data.value = result;
			}

			// 穿梭框数据
			for (let i = 0; i < data.value.length; i++) {
				detailData.transferData.push({
					key: data.value[i].id.toString(),
					title: data.value[i].menuName ?? "无名称",
				});
			}

			// 结束
			detailData.loadingState = false;
		};

		// 穿梭框过滤
		const transferFilterOption = (inputValue: string, option: TransferData) => {
			return option.title.indexOf(inputValue) > -1;
		};

		// 穿梭框变化处理
		const transferHandleChange = (keys: string[]) => {
			detailData.form.menuIds = keys.toString();
		};

		//------------------------------------------------------------------------------------------------------------------验证
		// 校验规则
		const rules = {
			roleName: [
				{
					required: true,
					validator: async (rule: RuleObject, value: string | undefined) => {
						if (value === undefined || value === "") {
							return Promise.reject("请输入角色名称！");
						} else {
							return Promise.resolve();
						}
					},
					trigger: "change",
				},
			],
			menuIds: [
				{
					required: true,
					validator: async (rule: RuleObject, value: string | undefined) => {
						if (value === undefined || value === "") {
							return Promise.reject("请选择菜单！");
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
			transferFilterOption,
			transferHandleChange,
		};
	},
});
</script>

<style scoped>
@import "@src/assets/css/button.css";

</style>

