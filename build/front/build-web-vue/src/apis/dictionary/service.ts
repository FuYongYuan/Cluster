import Request from "@src/utils/axios/request";
import {
	DictionaryApiBaseURL,
	SuccessRequestCode,
} from "@src/apis/commons/constant";
import { showErrorNotification } from "@src/antd/notification";
import type { PageImpl } from "@src/apis/commons/dto";
import type { ParameterDTO } from "@src/apis/dictionary/dto";

/**
 * 根据参数编码、参数名称、状态查询参数列表
 *
 * @param currentPage 当前页
 * @param eachPageSize 每页多少条
 * @param pageSort 分页排序
 * @param parameterCode 参数编码
 * @param parameterName 参数名称
 * @param state 状态
 */
export const queryParameter = async (
	currentPage: number,
	eachPageSize: number,
	pageSort?: string,
	parameterCode?: string,
	parameterName?: string,
	state?: number,
): Promise<PageImpl<ParameterDTO> | undefined> => {
	// 初始化
	const request = new Request<PageImpl<ParameterDTO>>();
	// 参数
	const params = {
		pageSort,
		parameterCode,
		parameterName,
		state,
	};
	// 地址
	const url = `${DictionaryApiBaseURL}/parameter/query/${currentPage}/${eachPageSize}`;
	// 请求
	return request.get(url, params).then((response) => {
		if (response.data.code === SuccessRequestCode) {
			return response.data.data;
		}
		showErrorNotification({
			message: "根据参数编码、参数名称、状态查询参数列表错误",
			description: response.data.message,
		});
	});
};

/**
 * 删除
 * @param ids ID集合
 */
export const deleteParameter = async (
	ids: string,
): Promise<number | undefined> => {
	// 初始化
	const request = new Request<number>();
	// 地址
	const url = `${DictionaryApiBaseURL}/parameter/delete/${ids}`;
	// 请求
	return request.delete(url).then((response) => {
		if (response.data.code === SuccessRequestCode) {
			return response.data.data;
		}
		showErrorNotification({
			message: "删除错误",
			description: response.data.message,
		});
	});
};

/**
 * 根据Id查询
 *
 * @param id ID
 */
export const getParameter = async (
	id: number,
): Promise<ParameterDTO | undefined> => {
	// 初始化
	const request = new Request<ParameterDTO>();
	// 地址
	const url = `${DictionaryApiBaseURL}/parameter/get/${id}`;
	// 请求
	return request.get(url).then((response) => {
		if (response.data.code === SuccessRequestCode) {
			return response.data.data;
		}
		showErrorNotification({
			message: "根据Id查询错误",
			description: response.data.message,
		});
	});
};

/**
 * 保存
 *
 * @param dto DTO
 */
export const saveParameter = async (
	dto: ParameterDTO,
): Promise<string | undefined> => {
	// 初始化
	const request = new Request<string>();
	// 地址
	const url = `${DictionaryApiBaseURL}/parameter/save`;
	// 请求
	return request.post(url, dto).then((response) => {
		if (response.data.code === SuccessRequestCode) {
			return response.data.data;
		}
		showErrorNotification({
			message: "保存错误",
			description: response.data.message,
		});
	});
};

/**
 * 根据参数编码、参数名称、状态查询参数列表导出
 *
 * @param pageSort 分页排序
 * @param parameterCode 参数编码
 * @param parameterName 参数名称
 * @param state 状态
 */
export const exportParameter = (
	pageSort?: string,
	parameterCode?: string,
	parameterName?: string,
	state?: number,
): Promise<void> => {
	// 初始化
	const request = new Request();
	// 参数
	const params = {
		pageSort,
		parameterCode,
		parameterName,
		state,
	};
	// 地址
	const url = `${DictionaryApiBaseURL}/parameter/export`;
	// 请求
	return request.export(url, params);
};
