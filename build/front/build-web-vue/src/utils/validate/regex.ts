/**
 * 邮箱格式正则验证
 * @param mail 邮箱
 */
export const mailFormatValidate = (mail: string) => {
	const regexMail =
		/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
	if (mail === undefined || mail === "") {
		return "请填写电子邮箱地址！";
	} else if (!regexMail.test(mail)) {
		return "电子邮箱格式不正确！";
	}
};

/**
 * 手机号码码格式正则验证
 * @param phone 手机号码
 */
export const phoneFormatValidate = (phone: string) => {
	const regexMail = /^1[3456789]\d{9}$/;
	if (phone === undefined || phone === "") {
		return "请填写手机号码！";
	} else if (!regexMail.test(phone)) {
		return "手机号码格式不正确！";
	}
};

/**
 * 验证图片正确性
 *
 * @param file 选中文件
 */
export const imageFormatValidate = (file: any) => {
	if (file !== undefined) {
		const isJpgOrPng = file.type === "image/jpeg" || file.type === "image/png";
		if (!isJpgOrPng) {
			return "请选择正确的图片格式文件！";
		}
		const isLt2M = file.size / 1024 / 1024 < 2;
		if (!isLt2M) {
			return "图片大小不能超过2MB！";
		}
		return undefined;
	} else {
		return "请选择图片文件！";
	}
};

/**
 * 验证JSON文件正确性
 *
 * @param file 选中文件
 */
export const jsonFormatValidate = (file: any) => {
	if (file !== undefined) {
		const isJson = file.type === "application/json";
		if (!isJson) {
			return "请选择正确的格式文件！";
		}
		const isLt2M = file.size / 1024 / 1024 < 2;
		if (!isLt2M) {
			return "文件大小不能超过2MB！";
		}
		return undefined;
	} else {
		return "请选择文件！";
	}
};

/**
 * 验证是否是数字
 *
 * @param value 值
 */
export const isNumber = (value: any) => {
	return typeof value === "number";
};
