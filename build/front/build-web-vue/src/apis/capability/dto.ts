/**
 * 菜单
 */
export type MenuDTO = {
	// 主键ID
	id: string;
	// 名称
	menuName?: string;
	// 路径
	menuUrl?: string;
	// 菜单图标
	menuIcon?: string;
	// 页面名称
	pageName?: string;
	// 父级ID
	parentId?: string;
	// 是否跳转  (0.否 1.是)
	isTurn?: number;
	// 是否首页  (0.否 1.是)
	isHome?: number;
	// 排序(按数字从小到大)
	menuOrder?: number;
	// 备注
	remark?: string;
	// 创建时间
	createTime?: Date;
	// 创建人名称
	creatorName?: string;
	// 修改时间
	updateTime?: Date;
	// 修改人名称
	updaterName?: string;
	// 状态(0.正常 99.删除)
	state?: number;
	// 名称
	parentMenuName?: string;
	// 子级菜单
	sub?: MenuDTO[];
};

/**
 * API信息
 */
export type ApiDTO = {
	// 主键ID
	id: string;
	// 类名
	className?: string;
	// 类Mapping值
	classMapping?: string;
	// 类Swagger说明
	classExplain?: string;
	// 方法名
	methodName?: string;
	// 方法Mapping值
	methodMapping?: string;
	// 方法Swagger说明
	methodExplain?: string;
	// 创建时间
	createTime?: Date;
	// 创建人名称
	creatorName?: string;
	// 修改时间
	updateTime?: Date;
	// 修改人名称
	updaterName?: string;
	// 状态(0.正常 99.删除)
	state?: number;
};
