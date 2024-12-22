declare module "DTO" {
	/**
	 * 图形验证码对象
	 */
	type ImageCaptchaDTO = {
		// 图片数据
		imageData: string;
		// 验证码缓存名称
		captchaCacheName: string;
	};

	/**
	 * 邮箱验证码对象
	 */
	type MailCaptchaDTO = {
		// 验证码缓存名称
		captchaCacheName: string;
	};
}
