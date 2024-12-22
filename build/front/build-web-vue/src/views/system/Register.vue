<template>
    <div class="login-container">
        <a-row>
            <a-col :xs="2" :md="4" :sm="6" :lg="8" :xl="16"></a-col>
            <a-col :xs="20" :sm="16" :md="12" :lg="8" :xl="6">
                <div class="login-container-form">
                    <a-form :model="form" :rules="rules" @finish="registerFinish">
                        <a-form-item has-feedback name="mail">
                            <a-input
                                    v-model:value="form.mail"
                                    placeholder="电子邮箱"
                            >
                                <template v-slot:prefix>
                                    <icon-font type="icon-mail"/>
                                </template>
                            </a-input>
                        </a-form-item>
                        <a-form-item has-feedback name="account">
                            <a-input
                                    v-model:value="form.account"
                                    placeholder="用户名"
                            >
                                <template v-slot:prefix>
                                    <icon-font type="icon-user"/>
                                </template>
                            </a-input>
                        </a-form-item>
                        <a-form-item has-feedback name="password">
                            <a-input-password
                                    v-model:value="form.password"
                                    placeholder="密码"
                            >
                                <template v-slot:prefix>
                                    <icon-font type="icon-lock"/>
                                </template>
                            </a-input-password>
                        </a-form-item>
                        <a-form-item has-feedback name="confirmPassword">
                            <a-input-password
                                    v-model:value="form.confirmPassword"
                                    placeholder="确认密码"
                            >
                                <template v-slot:prefix>
                                    <icon-font type="icon-key"/>
                                </template>
                            </a-input-password>
                        </a-form-item>
                        <a-form-item has-feedback name="imageCaptcha">
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
                        <a-form-item has-feedback name="mailCaptcha">
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
                        <a-form-item>
                            <a-button
                                    type="primary"
                                    shape="round"
                                    html-type="submit"
                                    class="button-side"
                                    :loading="registerLoading"
                            >
                                <template #icon>
                                    <icon-font type="icon-adduser"/>
                                </template>
                                注册
                            </a-button>
                            <a-button shape="round" html-type="button" href="/login">
                                <template #icon>
                                    <icon-font type="icon-rollback"/>
                                </template>
                                返回
                            </a-button>
                        </a-form-item>
                    </a-form>
                </div>
            </a-col>
            <a-col :xs="2" :md="4" :sm="6" :lg="8" :xl="2"></a-col>
        </a-row>
    </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, reactive, ref, toRefs } from "vue";
import { getImageCaptcha, getMailCaptcha } from "@src/apis/message/service";
import { FailImage } from "@src/apis/commons/constant";
import { message } from "ant-design-vue";
import { mailFormatValidate } from "@src/utils/validate/regex";
import { Md5 } from "ts-md5";
import { register } from "@src/apis/authorization/service";
import { useRouter } from "vue-router";
import { showSuccessNotification } from "@src/antd/notification";
import IconFont from "@src/assets/iconfont/icon";
import { RuleObject } from "ant-design-vue/es/form";
import { ImageCaptchaDTO, MailCaptchaDTO } from "DTO";

export default defineComponent({
	// 页面名称
	name: "Register",
	// 组件
	components: {
		// 图标库
		IconFont,
	},
	// 执行
	setup() {
		//------------------------------------------------------------------------------------------------------------------参数
		// 获取路由
		const router = useRouter();
		// 注册数据
		const registerData = reactive({
			// 表单
			form: {
				mail: "",
				account: "",
				password: "",
				confirmPassword: "",
				imageCaptcha: "",
				mailCaptcha: "",
			},
			// 图形验证码对象
			imageCaptchaDTO: ref<ImageCaptchaDTO>({
				imageData: FailImage,
				captchaCacheName: "",
			}),
			// 邮件验证码对象
			mailCaptchaDTO: ref<MailCaptchaDTO>({
				captchaCacheName: "",
			}),
			// 发送邮件验证码相关
			mailCaptchaText: "发送验证码",
			mailTotalTime: 60,
			mailLoading: false,
			// 注册按钮加载态
			registerLoading: false,
		});

		//------------------------------------------------------------------------------------------------------------------方法
		// 加载完成触发事件刷新图片验证码
		onMounted(async () => {
			await imageCaptchaClick();
		});

		// 验证码图片点击事件
		const imageCaptchaClick = async () => {
			const result = await getImageCaptcha();
			if (result !== undefined) {
				registerData.imageCaptchaDTO = result;
			}
		};

		// 邮箱验证码点击事件
		const mailCaptchaClick = async () => {
			// 校验
			if (registerData.form.mail.trim() === "") {
				return message.warning("电子邮箱不能为空！");
			} else if (mailFormatValidate(registerData.form.mail) !== undefined) {
				return message.warning("电子邮箱格式不正确！");
			}
			if (registerData.form.imageCaptcha.trim() === "") {
				return message.warning("图形验证码不能为空！");
			}
			//触发倒数
			registerData.mailLoading = true;
			registerData.mailCaptchaText = registerData.mailTotalTime + "s";
			const clock = window.setInterval(() => {
				registerData.mailTotalTime--;
				registerData.mailCaptchaText = registerData.mailTotalTime + "s";
				if (registerData.mailTotalTime < 0) {
					window.clearInterval(clock);
					registerData.mailCaptchaText = "发送验证码";
					registerData.mailTotalTime = 60;
					registerData.mailLoading = false;
				}
			}, 1000);

			// 发送邮件
			const result = await getMailCaptcha(
				registerData.form.mail,
				registerData.form.imageCaptcha,
				registerData.imageCaptchaDTO.captchaCacheName,
			);
			if (result !== undefined) {
				registerData.mailCaptchaDTO = result;
			} else {
				window.clearInterval(clock);
				registerData.mailCaptchaText = "发送验证码";
				registerData.mailTotalTime = 60;
				registerData.mailLoading = false;
			}
		};

		// 注册成功
		const registerFinish = async () => {
			const { mail, account, password, mailCaptcha } = registerData.form;
			// 开启加载态
			registerData.registerLoading = true;

			// 处理数据
			const loginPassword = Md5.hashStr(password);
			const mailCaptchaCacheName = registerData.mailCaptchaDTO.captchaCacheName;

			// 注册
			const result = await register(
				mail,
				account,
				loginPassword,
				mailCaptcha,
				mailCaptchaCacheName,
			);
			if (result) {
				showSuccessNotification({
					message: account + "注册成功",
					description: "恭喜" + mail + "注册成功！",
				});
				// 获取注册成功后要跳转的路由。
				await router.replace("/login");
			}

			// 关闭加载态
			registerData.registerLoading = false;
		};

		//------------------------------------------------------------------------------------------------------------------验证
		// 校验规则
		const rules = {
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
			password: [
				{
					required: true,
					validator: async (rule: RuleObject, value: string | undefined) => {
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
					validator: async (rule: RuleObject, value: string | undefined) => {
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
					validator: async (rule: RuleObject, value: string | undefined) => {
						if (value !== undefined && registerData.form.password !== value) {
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
					validator: async (rule: RuleObject, value: string | undefined) => {
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
					validator: async (rule: RuleObject, value: string | undefined) => {
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
			...toRefs(registerData),
			rules,
			imageCaptchaClick,
			mailCaptchaClick,
			registerFinish,
		};
	},
});
</script>

<style scoped>
.login-container {
  height: 100vh;
  background: url("~@public/images/login/login_bg.png");
  background-size: cover;

  &-form {
    width: calc(100% - 40px);
    height: 400px;
    padding: 1vh;
    margin-top: calc((100vh - 400px) / 2);
    margin-right: 20px;
    margin-left: 20px;
    background: #fff url("~@public/images/login/login_form.png");
    background-size: 100% 100%;
    border-radius: 10px;
    box-shadow: 0 2px 8px 0 rgba(7, 17, 27, 0.06);

    header {
      display: flex;
      align-items: center;
      justify-content: center;

      img {
        display: inline-block;
        width: 25px;
        margin-right: 5px;
      }

      h1 {
        margin-bottom: 0;
        font-size: 24px;
        color: #222;
        text-align: center;
      }
    }

    form {
      display: flex;
      flex-direction: column;
      align-items: center;
      width: 100%;
      margin-top: 10px;

      img {
        width: 80px;
        height: 20px;
      }
    }
  }

  .ant-form-item {
    width: 200px;
    text-align: center;
  }

  .button-side {
    margin-right: 20px
  }

  .button-link {
    padding: 4px 0;
  }

  .button-link-captcha {
    height: 20px;
    padding: 0;
  }
}
</style>