declare module "DTO" {
	/**
	 * Jwt返回对象
	 */
	type JwtDTO = {
		// 账号
		account: string;
		// 名称
		managerName?: string;
		// 头像
		headImgUrl?: string;
		// jwtToken
		jwtToken: string;
		// 失效时间
		invalidDate: date;
	};

	/**
	 * 角色
	 */
	type RoleDTO = {
		// 主键ID
		id: number;
		// 名称
		roleName?: string;
		// 备注
		remark?: string;
		// 创建时间
		createTime?: string;
		// 创建人名称
		creatorName?: string;
		// 修改时间
		updateTime?: string;
		// 修改人名称
		updaterName?: string;
		// 状态
		state?: number;
		// 菜单ID集合
		menuIds?: string;
	};
}
