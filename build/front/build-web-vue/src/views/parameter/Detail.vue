<template>
    <a-modal
            :visible="visible"
            :confirm-loading="loadingState"
            @cancel="handleCancel"
    >
        <template #title>
            <icon-font type="icon-detail"/>
            参数详情
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
                <a-form-item has-feedback label="参数编码" name="parameterCode">
                    <a-input
                            v-model:value="form.parameterCode"
                            placeholder="参数编码"
                            allowClear
                    >
                        <template v-slot:prefix>
                            <icon-font type="icon-code"/>
                        </template>
                    </a-input>
                </a-form-item>
                <a-form-item has-feedback label="参数名称" name="parameterName">
                    <a-input
                            v-model:value="form.parameterName"
                            placeholder="参数名称"
                            allowClear
                    >
                        <template v-slot:prefix>
                            <icon-font type="icon-Field-String"/>
                        </template>
                    </a-input>
                </a-form-item>
                <a-form-item label="参数值" name="parameterValue">
                    <a-textarea
                            v-model:value="form.parameterValue"
                            placeholder="参数值"
                            :maxlength="10000"
                            allowClear
                            showCount
                    />
                </a-form-item>
                <a-form-item label="参数说明" name="parameterExplain">
                    <a-textarea
                            v-model:value="form.parameterExplain"
                            placeholder="参数说明"
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
import { defineComponent, reactive, ref, toRefs } from "vue";
import IconFont from "@src/assets/iconfont/icon";
import { getParameter, saveParameter } from "@src/apis/dictionary/service";
import { FormInstance, message } from "ant-design-vue";
import { RuleObject } from "ant-design-vue/es/form";
import { ParameterDTO } from "DTO";

export default defineComponent({
	// 页面名称
	name: "ParameterDetail",
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
		//------------------------------------------------------------------------------------------------------------------参数
		// 详情DOM
		const detail = ref<FormInstance>();

		// 详情页面数据
		const detailData = reactive({
			// 表单
			form: ref<ParameterDTO>({
				id: -1,
			}),
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
					const result = await saveParameter(detailData.form);
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
				const result = await getParameter(searchId);
				if (result !== undefined) {
					detailData.form = result;
				}

				// 结束
				detailData.loadingState = false;
			}
		};

		//------------------------------------------------------------------------------------------------------------------验证
		// 校验规则
		const rules = {
			parameterCode: [
				{
					required: true,
					validator: async (rule: RuleObject, value: string | undefined) => {
						if (value === undefined || value === "") {
							return Promise.reject("请输入参数编码！");
						} else {
							return Promise.resolve();
						}
					},
					trigger: "change",
				},
			],
			parameterName: [
				{
					required: true,
					validator: async (rule: RuleObject, value: string | undefined) => {
						if (value === undefined || value === "") {
							return Promise.reject("请输入参数名称！");
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

