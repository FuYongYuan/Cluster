<template>
    <div class="login-container">
        <a-row>
            <a-col :xs="2" :md="4" :sm="6" :lg="8" :xl="16"></a-col>
            <a-col :xs="20" :sm="16" :md="12" :lg="8" :xl="6">
                <div class="login-container-form">
                    <header>
                        <img src="/images/logo.png" alt="logo"/>
                        <h1>哎呋歪歪平台</h1>
                    </header>
                    <a-form :model="form" :rules="rules" @finish="loginFinish">
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
                        <a-form-item style="margin-bottom: 0">
                            <a-button
                                    type="primary"
                                    shape="round"
                                    html-type="submit"
                                    class="button-side"
                                    :loading="loginLoading"
                            >
                                <template #icon>
                                    <icon-font type="icon-login"/>
                                </template>
                                登录
                            </a-button>
                            <a-button shape="round" html-type="button" class="button-side" href="/register">
                                <template #icon>
                                    <icon-font type="icon-adduser"/>
                                </template>
                                注册
                            </a-button>
                        </a-form-item>
                        <a-form-item> 忘记密码 ->
                            <a-button type="link" href="/recover" class="button-link">立即找回</a-button>
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
import { message } from "ant-design-vue";
import type { RuleObject } from "ant-design-vue/es/form";
import IconFont from "@src/assets/iconfont/icon";
import {
	FailImage,
	LocalStorageAccount,
	LocalStorageAccountMenuList,
	LocalStorageHeadImgUrl,
	LocalStorageInvalidDate,
	LocalStorageJwtToken,
	LocalStorageManagerName,
} from "@src/apis/commons/constant";
import { getImageCaptcha } from "@src/apis/message/service";
import { login, queryHaveMenu } from "@src/apis/authorization/service";
import type { ImageCaptchaDTO } from "@src/apis/message/dto";
import { Md5 } from "ts-md5/dist/esm/md5";
import { useRoute, useRouter } from "vue-router";

export default defineComponent({
	// 页面名称
	name: "Login",
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
		const route = useRoute();
		// 登录数据
		const loginData = reactive({
			// 表单
			form: {
				account: "",
				password: "",
				imageCaptcha: "",
			},
			// 图形验证码
			imageCaptchaDTO: ref<ImageCaptchaDTO>({
				imageData: FailImage,
				captchaCacheName: "",
			}),
			// 注册按钮加载态
			loginLoading: false,
		});

		//------------------------------------------------------------------------------------------------------------------方法
		// 页面加载时触发
		onMounted(async () => {
			// 清理 localStorage 和 sessionStorage
			localStorage.clear();
			sessionStorage.clear();
			// 加载完成触发事件刷新图片验证码
			await imageCaptchaClick();
		});

		// 验证码图片点击事件
		const imageCaptchaClick = async () => {
			const result = await getImageCaptcha();
			if (result !== undefined) {
				loginData.imageCaptchaDTO = result;
			}
		};

		// 登录成功
		const loginFinish = async () => {
			const { account, password, imageCaptcha } = loginData.form;
			// 开启加载态
			loginData.loginLoading = true;

			// 处理数据
			const loginPassword = Md5.hashStr(password);
			const imageCaptchaCacheName = loginData.imageCaptchaDTO.captchaCacheName;

			// 登录
			const result = await login(
				account,
				loginPassword,
				imageCaptcha,
				imageCaptchaCacheName,
			);
			if (result !== undefined) {
				message.success("登录成功");
				localStorage.setItem(LocalStorageAccount, result.account);
				if (
					result.headImgUrl !== undefined &&
					result.headImgUrl !== null &&
					result.headImgUrl !== ""
				) {
					localStorage.setItem(LocalStorageHeadImgUrl, result.headImgUrl);
				} else {
					localStorage.setItem(LocalStorageHeadImgUrl, FailImage);
				}
				if (
					result.managerName !== undefined &&
					result.managerName !== null &&
					result.managerName !== ""
				) {
					localStorage.setItem(LocalStorageManagerName, result.managerName);
				} else {
					localStorage.setItem(LocalStorageManagerName, result.account);
				}
				localStorage.setItem(LocalStorageJwtToken, result.jwtToken);
				localStorage.setItem(LocalStorageInvalidDate, result.invalidDate);

				const menuList = await queryHaveMenu();
				localStorage.setItem(
					LocalStorageAccountMenuList,
					JSON.stringify(menuList),
				);

				// 获取登录成功后要跳转的路由。
				const toPath = decodeURIComponent("/");
				router.replace(toPath).then(() => {
					if (route.name === "Login") {
						router.replace("/");
					}
				});
			}

			// 关闭加载态
			loginData.loginLoading = false;
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
		};

		//------------------------------------------------------------------------------------------------------------------返回
		return {
			...toRefs(loginData),
			rules,
			imageCaptchaClick,
			loginFinish,
		};
	},
});
</script>

<style scoped>
.login-container {
  height: 100vh;
  background: url("/images/login/login_bg.png");
  background-size: cover;
}

.login-container-form {
  width: calc(100% - 40px);
  height: 400px;
  padding: 3vh;
  margin-top: calc((100vh - 400px) / 2);
  margin-right: 20px;
  margin-left: 20px;
  background: #fff url("/images/login/login_form.png");
  background-size: 100% 100%;
  border-radius: 10px;
  box-shadow: 0 2px 8px 0 rgba(7, 17, 27, 0.06);
}

.login-container-form header {
  display: flex;
  align-items: center;
  justify-content: center;
}

.login-container-form header img {
  display: inline-block;
  width: 25px;
  margin-right: 5px;
}

.login-container-form header h1 {
  margin-bottom: 0;
  font-size: 24px;
  color: #222;
  text-align: center;
}

.login-container-form form {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  margin-top: 30px;
}

.login-container-form form img {
  width: 80px;
  height: 20px;
}

.ant-form-item {
  max-width: 186px;
}

.button-side {
  width: 100%;
  margin-bottom: 10px;
}

.button-link {
  padding: 4px 0;
}
</style>
