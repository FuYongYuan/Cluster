<template>
    <a-drawer
            placement="right"
            :closable="false"
            :visible="visible"
            @close="handleCancel"
    >
        <template #title>
            <icon-font type="icon-detail"/>
            个人信息详情
        </template>
        <template #footer>
            <a-space>
                <a-button class="ant-btn-daybreak" :loading="loadingState" @click="handleOk">
                    <icon-font type="icon-save" v-if="!loadingState"/>
                    保存
                </a-button>
                <a-button :loading="loadingState" @click="handleCancel">
                    <icon-font type="icon-close" v-if="!loadingState"/>
                    关闭
                </a-button>
            </a-space>
        </template>

        <a-spin tip="加载中......" :spinning="loadingState">
            <div>
                <a-row>
                    <a-col :span="10"></a-col>
                    <a-col :span="8">
                        <a-upload
                                v-model:fileList="fileList"
                                name="uploadFile"
                                list-type="picture-card"
                                accept=".jpeg,.gif,.png,.jpg"
                                :show-upload-list="false"
                                :customRequest="uploadData"
                                :before-upload="beforeUpload"
                                @change="handleChange"
                        >
                            <div>
                                <a-avatar v-if="form.headImgUrl"
                                          :size="{ xs: 24, sm: 32, md: 40, lg: 64, xl: 80, xxl: 100 }"
                                          :src="form.headImgUrl" shape="square">
                                </a-avatar>
                                <div v-else>
                                    <icon-font type="icon-reload" v-if="loadingState"/>
                                    <icon-font type="icon-image" v-else/>
                                    <div class="ant-upload-text">上传头像</div>
                                </div>
                                <a-progress v-if="headImgProgressShow" :percent="headImgProgress" :steps="6"
                                            :show-info="false"/>
                            </div>
                        </a-upload>
                    </a-col>
                    <a-col :span="6"></a-col>
                </a-row>
            </div>
            <a-form
                    ref="detail"
                    :model="form"
                    :rules="rules"
                    :label-col="{ span: 7 }"
                    :wrapper-col="{ span: 17 }"
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
            </a-form>
        </a-spin>
    </a-drawer>
</template>

<script lang="ts">
import { defineComponent, reactive, ref, toRefs } from "vue";
import IconFont from "@src/assets/iconfont/icon";
import { getManagerByJwtToken, saveManager } from "@src/apis/member/service";
import { FormInstance, message, UploadChangeParam } from "ant-design-vue";
import { ManagerDTO } from "DTO";
import { uploadFormData } from "@src/apis/data/service";
import {
	imageFormatValidate,
	mailFormatValidate,
	phoneFormatValidate,
} from "@src/utils/validate/regex";
import { RuleObject } from "ant-design-vue/es/form";
import { BusinessTypeUserHeadImage } from "@src/apis/commons/constant";

export default defineComponent({
	// 页面名称
	name: "ManagerDetailDrawer",
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
	emits: ["update:visible"],
	// 执行
	setup(props, context) {
		//------------------------------------------------------------------------------------------------------------------参数
		// 详情DOM
		const detail = ref<FormInstance>();

		// 详情页面数据
		const detailData = reactive({
			// 表单
			form: ref<ManagerDTO>({
				id: -1,
			}),
			// 文件列表
			fileList: ref<any>([]),
			// 加载状态
			loadingState: false,
			// 头像上传进度条
			headImgProgress: 0,
			// 头像进度是否显示
			headImgProgressShow: false,
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

					// 手机和邮箱验证
					if (detailData.form.mail !== undefined) {
						const validate = mailFormatValidate(detailData.form.mail);
						if (validate !== undefined) {
							message.error(validate);
							detailData.loadingState = false;
							return;
						}
					}
					if (detailData.form.mobile !== undefined) {
						const validate = phoneFormatValidate(detailData.form.mobile);
						if (validate !== undefined) {
							message.error(validate);
							detailData.loadingState = false;
							return;
						}
					}

					// 保存
					const result = await saveManager(detailData.form);
					// 保存结果
					message.success(result);

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

		// 根据JwtToken查询
		const getByJwtToken = async () => {
			// 开始
			detailData.loadingState = true;

			// 查询
			const result = await getManagerByJwtToken();
			if (result !== undefined) {
				detailData.form = result;
			}

			// 结束
			detailData.loadingState = false;
		};

		/**
		 * 处理头像变化结果
		 *
		 * @param info 处理内容，文件传输开始、完毕，进度条进展
		 */
		const handleChange = (info: UploadChangeParam) => {
			// 必定要加判断，否则会报错
			if (info.event !== undefined) {
				// 当前进度
				detailData.headImgProgress = info.event.percent;
			}
			if (info.file.status === "done") {
				detailData.headImgProgressShow = false;
				detailData.form.headImgUrl = info.file.response;
			}
		};

		/**
		 * 验证图片正确性
		 *
		 * @param file 选中文件
		 */
		const beforeUpload = (file: any) => {
			const validate = imageFormatValidate(file);
			if (validate !== undefined) {
				message.error(validate);
				return false;
			} else {
				return true;
			}
		};

		/**
		 * 文件上传
		 *
		 * @param options 上传相关内容
		 */
		const uploadData = async (options: any) => {
			const formData = new FormData();
			formData.append(options.filename, options.file);

			detailData.headImgProgressShow = true;

			const result = await uploadFormData(
				BusinessTypeUserHeadImage,
				formData,
				options,
			);

			if (result) {
				options.onSuccess(result);
			}
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
		};

		//------------------------------------------------------------------------------------------------------------------返回
		return {
			...toRefs(detailData),
			detail,
			rules,
			getByJwtToken,
			handleCancel,
			handleOk,
			// 方法
			handleChange,
			beforeUpload,
			uploadData,
		};
	},
});
</script>

<style scoped>
@import "~@src/assets/css/button.css";

</style>
