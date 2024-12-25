/**
 * 图形验证码对象
 */
export type ImageCaptchaDTO = {
	// 图片数据
	imageData: string;
	// 验证码缓存名称
	captchaCacheName: string;
};

/**
 * 邮箱验证码对象
 */
export type MailCaptchaDTO = {
	// 验证码缓存名称
	captchaCacheName: string;
};
