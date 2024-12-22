import Request from "@src/utils/axios/request";
import { DataBaseURL, SuccessRequestCode } from "@src/apis/commons/constant";
import { showErrorNotification } from "@src/antd/notification";
import type { ResultMessage } from "DTO";

/**
 * 文件上传
 *
 * @param businessType 业务类型
 * @param formData 文件数据
 * @param options 上传进度处理
 */
export const uploadFormData = (
	businessType: string,
	formData: FormData,
	options: any,
): Promise<string> => {
	// 初始化
	const request = new Request<ResultMessage<string>>();
	// 地址
	const url = `${DataBaseURL}/file/upload/get/url/${businessType}`;
	// 请求
	return request
		.upload(url, formData, (progressEvent) => {
			// 进度
			const progress = { percent: 1 };
			// 单一次跳动计算公式
			const speed = Number.parseInt(
				(
					((100 / (options.file.size / 65000)) * progressEvent.loaded) /
					progressEvent.total
				).toFixed(0),
			);
			// 定时跳动进度条
			const intervalId = setInterval(() => {
				// 控制进度条防止在未上传成功时进度条达到100
				if (progress.percent < 99 && progress.percent + speed < 100) {
					progress.percent += speed;
					options.onProgress(progress);
				} else if (progress.percent === 99) {
					progress.percent++;
				} else if (progress.percent >= 100 || progress.percent + speed >= 100) {
					clearInterval(intervalId);
				}
			}, 100);
		})
		.then((response) => {
			if (response.data.code === SuccessRequestCode) {
				return response.data.data;
			}
			showErrorNotification({
				message: "上传错误",
				description: response.data.message,
			});
		});
};
