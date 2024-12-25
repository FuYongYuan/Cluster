/**
 * API接口返回统一对象
 */
export type ResultMessage<T> = {
	// 状态码
	code: number;
	// 返回数据
	data?: T;
	// 返回消息
	message: string;
};

/**
 * 穿梭框数据类型
 */
export type TransferData = {
	key: string;
	title: string;
	description?: string;
	chosen?: boolean;
	disabled?: boolean;
};

/**
 * 排序DTO
 */
export type PageSortDTO = {
	// 排序优先级
	multiple: number;
	// 字段名称
	field: string;
	// 排序方式
	order: string;
};

/**
 * 分页对象
 */
export type PageImpl<T> = {
	content: T[];
	empty: boolean;
	first: boolean;
	last: boolean;
	number: number;
	numberOfElements: number;
	pageable: Pageable;
	size: number;
	sort: Sort;
	totalElements: number;
	totalPages: number;
};

/**
 * 可分页的信息
 */
export type Pageable = {
	offset: number;
	pageNumber: number;
	pageSize: number;
	paged: boolean;
	sort: Sort;
	unpaged: boolean;
};

/**
 * 排序信息
 */
export type Sort = {
	empty: boolean;
	sorted: boolean;
	unsorted: boolean;
};
