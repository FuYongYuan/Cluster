<template>
  <a-drawer
      placement="right"
      :closable="false"
      :open="open"
      @close="handleCancel"
  >
    <template #title>
      <icon-font type="icon-detail"/>
      修改密码
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
      <a-form
          ref="detail"
          :model="form"
          :rules="rules"
          :label-col="{ span: 7 }"
          :wrapper-col="{ span: 17 }"
      >
        <a-form-item has-feedback label="邮箱" name="mail">
          <a-input
              v-model:value="form.mail"
              placeholder="电子邮箱"
              disabled
          >
            <template v-slot:prefix>
              <icon-font type="icon-mail"/>
            </template>
          </a-input>
        </a-form-item>
        <a-form-item has-feedback label="新密码" name="password">
          <a-input-password
              v-model:value="form.password"
              placeholder="新密码"
          >
            <template v-slot:prefix>
              <icon-font type="icon-lock"/>
            </template>
          </a-input-password>
        </a-form-item>
        <a-form-item has-feedback label="确认密码" name="confirmPassword">
          <a-input-password
              v-model:value="form.confirmPassword"
              placeholder="确认密码"
          >
            <template v-slot:prefix>
              <icon-font type="icon-key"/>
            </template>
          </a-input-password>
        </a-form-item>
        <a-form-item has-feedback label="图形验证码" name="imageCaptcha">
          <a-input
              v-model:value="form.imageCaptcha"
              placeholder="验证码"
          >
            <template v-slot:prefix>
              <icon-font type="icon-image"/>
            </template>
            <template v-slot:suffix>
              <img
                  :src="imageCaptchaDTO.imageData"
                  alt="验证码"
                  @click="imageCaptchaClick"
              />
            </template>
          </a-input>
        </a-form-item>
        <a-form-item has-feedback label="邮箱验证码" name="mailCaptcha">
          <a-input
              v-model:value="form.mailCaptcha"
              placeholder="验证码"
          >
            <template v-slot:prefix>
              <icon-font type="icon-codelibrary"/>
            </template>
            <template v-slot:suffix>
              <a-button type="text" class="button-link-captcha" :loading="mailLoading"
                        @click="mailCaptchaClick">{{ mailCaptchaText }}
              </a-button>
            </template>
          </a-input>
        </a-form-item>
      </a-form>
    </a-spin>
  </a-drawer>
</template>

<script lang="ts">
import { defineComponent, onMounted, reactive, ref, toRefs } from "vue";
import { FormInstance, message } from "ant-design-vue";
import { RuleObject } from "ant-design-vue/es/form";
import IconFont from "@src/assets/iconfont/icon";
import { FailImage } from "@src/apis/commons/constant";
import { getManagerByJwtToken } from "@src/apis/member/service";
import { getImageCaptcha, getMailCaptcha } from "@src/apis/message/service";
import type { ImageCaptchaDTO, MailCaptchaDTO } from "@src/apis/message/dto";
import { mailFormatValidate } from "@src/utils/validate/regex";
import { Md5 } from "ts-md5";
import { recover } from "@src/apis/authorization/service";
import { showSuccessNotification } from "@src/antd/notification";
import { useRouter } from "vue-router";

export default defineComponent({
	// 页面名称
	name: "ChangePasswordDrawer",
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
	emits: ["update:open"],
	// 执行
	setup(_, context) {
		//------------------------------------------------------------------------------------------------------------------参数
		// 详情DOM
		const detail = ref<FormInstance>();

		// 详情页面数据
		const detailData = reactive({
			// 表单
			form: {
				// Id
				id: -1,
				// 账号
				mail: "",
				// 密码
				password: "",
				// 确认密码
				confirmPassword: "",
				// 验证码
				imageCaptcha: "",
				// 邮箱验证码
				mailCaptcha: "",
			},
			// 图形验证码对象
			imageCaptchaDTO: ref<ImageCaptchaDTO>({
				imageData: FailImage,
				captchaCacheName: "",
			}),
			// 邮件验证码
			mailCaptchaDTO: ref<MailCaptchaDTO>({
				captchaCacheName: "",
			}),
			// 发送邮件验证码相关
			mailCaptchaText: "发送验证码",
			mailTotalTime: 60,
			mailLoading: false,
			// 加载状态
			loadingState: false,
		});

		//------------------------------------------------------------------------------------------------------------------方法
		// 获取路由
		const router = useRouter();
		// 加载完成触发事件刷新图片验证码
		onMounted(async () => {
			await imageCaptchaClick();
		});

		// 验证码图片点击事件
		const imageCaptchaClick = async () => {
			const result = await getImageCaptcha();
			if (result !== undefined) {
				detailData.imageCaptchaDTO = result;
			}
		};

		// 邮箱验证码点击事件
		const mailCaptchaClick = async () => {
			// 校验
			if (detailData.form.mail.trim() === "") {
				return message.warning("电子邮箱不能为空！");
			} else if (mailFormatValidate(detailData.form.mail) !== undefined) {
				return message.warning("电子邮箱格式不正确！");
			}
			if (detailData.form.imageCaptcha.trim() === "") {
				return message.warning("图形验证码不能为空！");
			}
			// 触发倒数
			detailData.mailLoading = true;
			detailData.mailCaptchaText = detailData.mailTotalTime + "s";
			const clock = window.setInterval(() => {
				detailData.mailTotalTime--;
				detailData.mailCaptchaText = detailData.mailTotalTime + "s";
				if (detailData.mailTotalTime < 0) {
					window.clearInterval(clock);
					detailData.mailCaptchaText = "发送验证码";
					detailData.mailTotalTime = 60;
					detailData.mailLoading = false;
				}
			}, 1000);
			// 发送邮件
			const result = await getMailCaptcha(
				detailData.form.mail,
				detailData.form.imageCaptcha,
				detailData.imageCaptchaDTO.captchaCacheName,
			);
			if (result !== undefined) {
				detailData.mailCaptchaDTO = result;
			} else {
				window.clearInterval(clock);
				detailData.mailCaptchaText = "发送验证码";
				detailData.mailTotalTime = 60;
				detailData.mailLoading = false;
			}
		};

		// 关闭方法
		const handleCancel = () => {
			// 关闭显示
			context.emit("update:open", false);
			// 清理表单
			detailData.form = {
				id: -1,
				mail: "",
				password: "",
				confirmPassword: "",
				imageCaptcha: "",
				mailCaptcha: "",
			};
			// 重新加载图片验证码
			imageCaptchaClick();

			detail.value?.resetFields();
		};

		// 提交方法
		const handleOk = async () => {
			detail.value
				?.validate()
				.then(async () => {
					const { mail, password, mailCaptcha } = detailData.form;
					// 开启加载态
					detailData.loadingState = true;

					// 处理数据
					const loginPassword = Md5.hashStr(password);
					const mailCaptchaCacheName =
						detailData.mailCaptchaDTO.captchaCacheName;

					// 找回密码
					const result = await recover(
						mail,
						loginPassword,
						mailCaptcha,
						mailCaptchaCacheName,
					);
					if (result) {
						showSuccessNotification({
							message: "找回密码成功",
							description: "找回密码成功，请前往登录页重新登录！",
						});
						// 获取找回密码成功后要跳转的路由。
						await router.replace("/login");
					}

					// 关闭加载态
					detailData.loadingState = false;
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
				if (result.id > 0) {
					detailData.form.id = result.id;
				}
				if (result.mail !== undefined) {
					detailData.form.mail = result.mail;
				}
			}

			// 结束
			detailData.loadingState = false;
		};

		//------------------------------------------------------------------------------------------------------------------验证
		// 校验规则
		const rules = {
			mail: [
				{
					required: true,
					validator: async (_: RuleObject, value: string | undefined) => {
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
			password: [
				{
					required: true,
					validator: async (_: RuleObject, value: string | undefined) => {
						if (value === undefined || value === "") {
							return Promise.reject("请输入密码！");
						} else {
							return Promise.resolve();
						}
					},
					trigger: "change",
				},
			],
			confirmPassword: [
				{
					required: true,
					validator: async (_: RuleObject, value: string | undefined) => {
						if (value === undefined || value === "") {
							return Promise.reject("请输入确认密码！");
						} else {
							return Promise.resolve();
						}
					},
					trigger: "change",
				},
				{
					required: true,
					validator: async (_: RuleObject, value: string | undefined) => {
						if (value !== undefined && detailData.form.password !== value) {
							return Promise.reject("确认密码与密码不一致！");
						} else {
							return Promise.resolve();
						}
					},
					trigger: ["change", "blur"],
				},
			],
			imageCaptcha: [
				{
					required: true,
					validator: async (_: RuleObject, value: string | undefined) => {
						if (value === undefined || value === "") {
							return Promise.reject("请输入验证码！");
						} else {
							return Promise.resolve();
						}
					},
					trigger: "change",
				},
			],
			mailCaptcha: [
				{
					required: true,
					validator: async (_: RuleObject, value: string | undefined) => {
						if (value === undefined || value === "") {
							return Promise.reject("请输入验证码！");
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
			getByJwtToken,
			handleCancel,
			handleOk,
			imageCaptchaClick,
			mailCaptchaClick,
		};
	},
});
</script>

<style scoped>
@import "@src/assets/css/button.css";

img {
  width: 80px;
  height: 20px;
}

.button-link-captcha {
  height: 20px;
  padding: 0;
}
</style>
