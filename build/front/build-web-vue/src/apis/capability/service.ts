import Request from "@src/utils/axios/request";
import {
	CapabilityApiBaseURL,
	SuccessRequestCode,
} from "@src/apis/commons/constant";
import { showErrorNotification } from "@src/antd/notification";
import type { PageImpl } from "@src/apis/commons/dto";
import type { MenuDTO } from "@src/apis/capability/dto";

/**
 * 查询所有菜单
 */
export const queryMenuAll = (): Promise<MenuDTO[] | undefined> => {
	const request = new Request<MenuDTO[]>();
	return request
		.get(`${CapabilityApiBaseURL}/menu/query/all`)
		.then((response) => {
			if (response.data.code === SuccessRequestCode) {
				return response.data.data;
			}
			showErrorNotification({
				message: "查询所有菜单错误",
				description: response.data.message,
			});
		});
};

/**
 * 查询所有父级菜单
 */
export const queryMenuAllParent = (): Promise<MenuDTO[] | undefined> => {
	const request = new Request<MenuDTO[]>();
	return request
		.get(`${CapabilityApiBaseURL}/menu/query/all/parent`)
		.then((response) => {
			if (response.data.code === SuccessRequestCode) {
				return response.data.data;
			}
			showErrorNotification({
				message: "查询所有父级菜单错误",
				description: response.data.message,
			});
		});
};

/**
 * 根据菜单名称、状态查询参数列表
 *
 * @param currentPage 当前页
 * @param eachPageSize 每页多少条
 * @param pageSort 分页排序
 * @param menuName 菜单名称
 * @param state 状态
 */
export const queryMenu = (
	currentPage: number,
	eachPageSize: number,
	pageSort?: string,
	menuName?: string,
	state?: number,
): Promise<PageImpl<MenuDTO> | undefined> => {
	// 初始化
	const request = new Request<PageImpl<MenuDTO>>();
	// 参数
	const params = {
		pageSort,
		menuName,
		state,
	};
	// 地址
	const url = `${CapabilityApiBaseURL}/menu/query/${currentPage}/${eachPageSize}`;
	// 请求
	return request.get(url, params).then((response) => {
		if (response.data.code === SuccessRequestCode) {
			return response.data.data;
		}
		showErrorNotification({
			message: "根据菜单名称、状态查询参数列表错误",
			description: response.data.message,
		});
	});
};

/**
 * 删除
 * @param ids ID集合
 */
export const deleteMenu = (ids: string): Promise<number | undefined> => {
	// 初始化
	const request = new Request<number>();
	// 地址
	const url = `${CapabilityApiBaseURL}/menu/delete/${ids}`;
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
export const getMenu = (id: number): Promise<MenuDTO | undefined> => {
	// 初始化
	const request = new Request<MenuDTO>();
	// 地址
	const url = `${CapabilityApiBaseURL}/menu/get/${id}`;
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
export const saveMenu = (dto: MenuDTO): Promise<string | undefined> => {
	// 初始化
	const request = new Request<string>();
	// 地址
	const url = `${CapabilityApiBaseURL}/menu/save`;
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
