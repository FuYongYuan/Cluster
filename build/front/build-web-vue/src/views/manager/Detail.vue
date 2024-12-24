<template>
    <a-modal
            :visible="visible"
            :confirm-loading="loadingState"
            @cancel="handleCancel"
    >
        <template #title>
            <icon-font type="icon-detail"/>
            管理员详情
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
                <a-form-item has-feedback label="账号" name="account">
                    <a-input
                            v-model:value="form.account"
                            placeholder="账号"
                            disabled
                    >
                        <template v-slot:prefix>
                            <icon-font type="icon-cloud"/>
                        </template>
                    </a-input>
                </a-form-item>

                <a-form-item has-feedback label="管理员名称" name="managerName">
                    <a-input
                            v-model:value="form.managerName"
                            placeholder="管理员名称"
                            allowClear
                    >
                        <template v-slot:prefix>
                            <icon-font type="icon-user"/>
                        </template>
                    </a-input>
                </a-form-item>

                <a-form-item has-feedback label="年龄" name="age">
                    <a-input
                            v-model:value="form.age"
                            placeholder="年龄"
                            type="number"
                            allowClear
                    >
                        <template v-slot:prefix>
                            <icon-font type="icon-Field-Binary"/>
                        </template>
                    </a-input>
                </a-form-item>

                <a-form-item has-feedback label="性别" name="sex">
                    <a-radio-group v-model:value="form.sex" button-style="solid">
                        <a-radio-button :value=0>保密</a-radio-button>
                        <a-radio-button :value=1>男</a-radio-button>
                        <a-radio-button :value=2>女</a-radio-button>
                    </a-radio-group>
                </a-form-item>

                <a-form-item has-feedback label="手机号码" name="mobile">
                    <a-input
                            v-model:value="form.mobile"
                            placeholder="手机号码"
                            allowClear
                    >
                        <template v-slot:prefix>
                            <icon-font type="icon-mobile"/>
                        </template>
                    </a-input>
                </a-form-item>

                <a-form-item has-feedback label="邮箱" name="mail">
                    <a-input
                            v-model:value="form.mail"
                            placeholder="邮箱"
                            allowClear
                    >
                        <template v-slot:prefix>
                            <icon-font type="icon-mail"/>
                        </template>
                    </a-input>
                </a-form-item>

                <a-form-item has-feedback label="角色" name="roleIds">
                    <a-transfer
                            v-model:selected-keys="transferSelected"
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
import IconFont from "@src/assets/iconfont/icon";
import { getManager, saveManager } from "@src/apis/member/service";
import { FormInstance, message } from "ant-design-vue";
import { ManagerDTO, RoleDTO, TransferData } from "DTO";
import { queryRoleAll } from "@src/apis/authorization/service";
import { RuleObject } from "ant-design-vue/es/form";
import {
	mailFormatValidate,
	phoneFormatValidate,
} from "@src/utils/validate/regex";

export default defineComponent({
	// 页面名称
	name: "ManagerDetail",
	// 组件
	components: {
		// 图标库
		IconFont,
	},
	// 父组件传递值
	props: {
		// 是否显示
		visible: {
			type: Boolean,
			default: false,
		},
	},
	// 发出更新
	emits: ["update:visible", "query"],
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
			form: ref<ManagerDTO>({
				id: -1,
			}),
			// 穿梭框选中
			transferSelected: ref<string[]>([]),
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
			context.emit("update:visible", false);
			// 清理表单
			detailData.form = {
				id: -1,
			};
			detailData.transferSelected = [];
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
					const result = await saveManager(detailData.form);
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
				const result = await getManager(searchId);
				if (result !== undefined) {
					detailData.form = result;
					if (result.roleIds !== undefined) {
						detailData.transferHave = result.roleIds.split(",");
					}
				}

				// 结束
				detailData.loadingState = false;
			}
		};

		// 查询所有角色
		const transferQueryData = async () => {
			// 开始
			detailData.loadingState = true;

			const data = ref<RoleDTO[]>([]);

			// 查询
			const result = await queryRoleAll();
			if (result !== undefined) {
				data.value = result;
			}

			// 穿梭框数据
			for (let i = 0; i < data.value.length; i++) {
				detailData.transferData.push({
					key: data.value[i].id.toString(),
					title: data.value[i].roleName ?? "无名称",
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
			detailData.form.roleIds = keys.toString();
		};

		//------------------------------------------------------------------------------------------------------------------验证
		// 校验规则
		const rules = {
			account: [
				{
					required: true,
					validator: async (rule: RuleObject, value: string | undefined) => {
						if (value === undefined || value === "") {
							return Promise.reject("请输入账号！");
						} else {
							return Promise.resolve();
						}
					},
					trigger: "change",
				},
			],
			managerName: [
				{
					required: true,
					validator: async (rule: RuleObject, value: string | undefined) => {
						if (value === undefined || value === "") {
							return Promise.reject("请输入管理员名称！");
						} else {
							return Promise.resolve();
						}
					},
					trigger: "change",
				},
			],
			mail: [
				{
					required: true,
					validator: async (rule: RuleObject, value: string | undefined) => {
						if (value === undefined || value === "") {
							return Promise.reject("请输入电子邮箱！");
						} else {
							const result = mailFormatValidate(value);
							if (result !== undefined) {
								return Promise.reject(result);
							} else {
								return Promise.resolve();
							}
						}
					},
					trigger: "change",
				},
			],
			mobile: [
				{
					validator: async (rule: RuleObject, value: string | undefined) => {
						if (value === undefined || value === "") {
							return Promise.reject("请输入手机号码！");
						} else {
							const result = phoneFormatValidate(value);
							if (result !== undefined) {
								return Promise.reject(result);
							} else {
								return Promise.resolve();
							}
						}
					},
					trigger: "change",
				},
			],
			roleIds: [
				{
					required: true,
					validator: async (rule: RuleObject, value: string | undefined) => {
						if (value === undefined || value === "") {
							return Promise.reject("请选择角色！");
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

