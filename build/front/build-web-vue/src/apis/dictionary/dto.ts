/**
 * 参数
 */
export type ParameterDTO = {
	// 主键ID
	id: number;
	// 参数代码
	parameterCode?: string;
	// 参数名称
	parameterName?: string;
	// 参数值
	parameterValue?: string;
	// 参数说明
	parameterExplain?: string;
	// 创建时间
	createTime?: date;
	// 创建人名称
	creatorName?: string;
	// 修改时间
	updateTime?: date;
	// 修改人名称
	updaterName?: string;
	// 状态(0.正常 99.删除)
	state?: number;
};
