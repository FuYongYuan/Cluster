import axios, {
	AxiosPromise,
	AxiosRequestConfig,
	AxiosResponse,
	InternalAxiosRequestConfig,
} from "axios";
import { showErrorNotification } from "@src/antd/notification";
import {
	AuthorizationOverdueCode,
	JwtTokenBearerName,
	JwtTokenRequestHeaderName,
	LocalStorageJwtToken,
} from "@src/apis/commons/constant";
import type { ResultMessage } from "@src/apis/commons/dto";

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
			config.headers[JwtTokenRequestHeaderName] =
				`${JwtTokenBearerName} ${jwtToken}`;
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
			window.location.href = "/login";
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
		showErrorNotification({
			message: "请求错误",
			description: error.message,
		});
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
	export = (url: string, params?: any): Promise<void> => {
		const config: AxiosRequestConfig = {
			method: "POST",
			data: params,
			url: url,
			responseType: "blob", // 设置为 Blob 类型以便处理文件下载
		};
		return service(url, config).then((response) => {
			const url = window.URL.createObjectURL(new Blob([response.data]));
			const link = document.createElement("a");
			link.href = url;
			link.download =
				decodeURI(
					response.headers["content-disposition"].split(";")[1].split("=")[1],
				) || "无名.xlsx";
			link.click();
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
