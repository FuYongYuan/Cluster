import { showErrorNotification } from "@src/antd/notification";
import {
	AuthorizationOverdueCode,
	JwtTokenBearerName,
	JwtTokenRequestHeaderName,
	LocalStorageJwtToken,
} from "@src/apis/commons/constant";
import type { ResultMessage } from "@src/apis/commons/dto";
import router from "@src/router/config";
import axios, {
	type AxiosPromise,
	type AxiosRequestConfig,
	type AxiosResponse,
	type InternalAxiosRequestConfig,
} from "axios";

// 设置请求参数
const timeOut = 1000 * 1000 * 60;

// 创建实例
const service = axios.create({
	timeout: timeOut,
});

// 添加请求拦截器
service.interceptors.request.use(
	(config: InternalAxiosRequestConfig) => {
		const jwtToken = localStorage.getItem(LocalStorageJwtToken);

		// 改为使用 InternalAxiosRequestConfig 类型来避免类型冲突
		if (jwtToken && config?.headers) {
			// 设置请求头
			config.headers[JwtTokenRequestHeaderName] =
				`${JwtTokenBearerName} ${jwtToken}`;

			// 设置菜单权限
		}

		return config;
	},
	(error: any) => {
		showErrorNotification({
			message: "请求错误",
			description: error.message,
		});
		return Promise.reject(error);
	},
);

// 添加响应拦截器
service.interceptors.response.use(
	(response: AxiosResponse) => {
		if (response.data.code === AuthorizationOverdueCode) {
			showErrorNotification({
				message: "登录过期",
				description: "登录已过期，请重新登录",
			});
			localStorage.removeItem(LocalStorageJwtToken);
			window.location.href = "/login";
			return Promise.reject(new Error("登录过期"));
		}
		const jwtToken = localStorage.getItem(LocalStorageJwtToken);
		const authorization = `${JwtTokenBearerName} ${jwtToken}`;
		const responseHeaderAuthorization =
			response.headers[JwtTokenRequestHeaderName];

		if (
			jwtToken &&
			authorization &&
			responseHeaderAuthorization &&
			authorization !== responseHeaderAuthorization
		) {
			const refreshJwtToken = responseHeaderAuthorization.replace(
				`${JwtTokenBearerName} `,
				"",
			);
			localStorage.setItem(LocalStorageJwtToken, refreshJwtToken);
		}

		return response;
	},
	(error: any) => {
		if (error.response) {
			const status: number = error.response.status;
			switch (status) {
				case 401:
					showErrorNotification({
						message: "登录过期",
						description: "登录已过期，请重新登录",
					});
					localStorage.removeItem(LocalStorageJwtToken);
					window.location.href = "/login";
					break;
				case 403:
					showErrorNotification({
						message: "拒绝访问",
						description: "您没有权限访问该资源",
					});
					break;
				case 404:
					showErrorNotification({
						message: "请求失败",
						description: "请求的资源不存在 (404)",
					});
					router.push("/error/404").then(() => {});
					break;
				case 500:
					showErrorNotification({
						message: "服务器错误",
						description: "服务器内部错误，请稍后重试 (500)",
					});
					break;
				case 502:
					showErrorNotification({
						message: "网关错误",
						description: "网关错误，请稍后重试 (502)",
					});
					break;
				case 503:
					showErrorNotification({
						message: "服务不可用",
						description: "服务暂时不可用，请稍后重试 (503)",
					});
					break;
				default:
					showErrorNotification({
						message: "请求失败",
						description: `请求异常 (${status})，请稍后重试`,
					});
					break;
			}
		} else if (error.request) {
			showErrorNotification({
				message: "网络异常",
				description: "无法连接到服务器，请检查网络连接",
			});
		} else {
			showErrorNotification({
				message: "请求错误",
				description: error.message,
			});
		}
		return Promise.reject(error);
	},
);

//添加 request 类
class Request<T = any> {
	// get 请求
	get = (url: string, params?: any): AxiosPromise<ResultMessage<T>> => {
		const config: AxiosRequestConfig = {
			method: "GET",
			params: params,
			url: url,
		};
		return service(url, config);
	};

	// post 请求
	post = (url: string, params?: any): AxiosPromise<ResultMessage<T>> => {
		const config: AxiosRequestConfig = {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
			},
			data: params,
			url: url,
		};
		return service(url, config);
	};

	// put 请求
	put = (url: string, params?: any): AxiosPromise<ResultMessage<T>> => {
		const config: AxiosRequestConfig = {
			method: "PUT",
			data: params,
			url: url,
		};
		return service(url, config);
	};

	// delete 请求
	delete = (url: string, params?: any): AxiosPromise<ResultMessage<T>> => {
		const config: AxiosRequestConfig = {
			method: "DELETE",
			data: params,
			url: url,
		};
		return service(url, config);
	};

	// 导出
	export = async (url: string, params?: any): Promise<void> => {
		const config: AxiosRequestConfig = {
			method: "POST",
			params: params,
			data: params,
			url: url,
			responseType: "blob", // 设置为 Blob 类型以便处理文件下载
		};
		return service(url, config).then((response) => {
			// 检查 content-disposition 是否存在
			const disposition = response.headers["content-disposition"];
			let filename = "无名.xlsx";

			if (disposition && disposition.indexOf("filename=") !== -1) {
				// 提取文件名
				filename = decodeURI(
					disposition
						.split(";")
						.find((part: string) => part.trim().startsWith("filename="))
						?.split("=")[1]
						.trim(),
				);
			}

			const urlBlob = window.URL.createObjectURL(new Blob([response.data]));
			const link = document.createElement("a");
			link.href = urlBlob;
			link.download = filename;
			link.click();

			// 释放对象 URL
			window.URL.revokeObjectURL(urlBlob);
		});
	};

	// 上传
	upload = (
		url: string,
		formData: any,
		uploadProgress: (progressEvent: any) => void,
	): AxiosPromise => {
		const config: AxiosRequestConfig = {
			method: "POST",
			headers: {
				"Content-Type": "multipart/form-data",
			},
			data: formData,
			url: url,
			onUploadProgress: uploadProgress,
		};
		return service(url, config);
	};
}

export default Request;
