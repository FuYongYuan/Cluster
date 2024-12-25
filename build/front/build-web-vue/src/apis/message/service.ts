import Request from "@src/utils/axios/request";
import {
	DataImagePNG,
	MessageApiBaseURL,
	SuccessRequestCode,
} from "@src/apis/commons/constant";
import { showErrorNotification } from "@src/antd/notification";
import type { ImageCaptchaDTO, MailCaptchaDTO } from "@src/apis/message/dto";

/**
 * 获取图形验证码
 */
export const getImageCaptcha = (): Promise<ImageCaptchaDTO | undefined> => {
	const request = new Request<ImageCaptchaDTO>();
	return request
		.get(`${MessageApiBaseURL}/image/get/captcha`)
		.then((response) => {
			if (response.data.code === SuccessRequestCode) {
				if (response.data.data !== undefined) {
					response.data.data.imageData =
						DataImagePNG + response.data.data.imageData;
				}
				return response.data.data;
			}
			showErrorNotification({
				message: "获取验证码错误",
				description: response.data.message,
			});
		});
};

/**
 * 获取邮件验证码
 * @param mail 邮箱地址
 * @param imageCaptcha 图形验证码
 * @param imageCaptchaCacheName 图形验证码缓存名称
 */
export const getMailCaptcha = (
	mail: string,
	imageCaptcha: string,
	imageCaptchaCacheName: string,
): Promise<MailCaptchaDTO | undefined> => {
	const request = new Request<MailCaptchaDTO>();
	return request
		.get(
			`${MessageApiBaseURL}/mail/send/captcha/${mail}/${imageCaptcha}/${imageCaptchaCacheName}`,
		)
		.then((response) => {
			if (response.data.code === SuccessRequestCode) {
				return response.data.data;
			}
			showErrorNotification({
				message: "获取验证码错误",
				description: response.data.message,
			});
		});
};
