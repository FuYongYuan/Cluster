declare module "DTO" {
	/**
	 * 管理员
	 */
	type ManagerDTO = {
		// 主键ID
		id: number;
		// 头像
		headImgUrl?: string;
		// 姓名
		managerName?: string;
		// 年龄
		age?: number;
		// 性别(0.保密 1.男 2女)
		sex?: number;
		// 手机号码
		mobile?: string;
		// 邮箱
		mail?: string;
		// 账号
		account?: string;
		// 当前请求IP
		currentRequestIp?: string;
		// 备注
		remark?: string;
		// 创建时间
		createTime?: date;
		// 创建人名称
		creatorName?: string;
		// 修改时间
		updateTime?: date;
		// 修改人名称
		updaterName?: string;
		// 状态(0.正常 1.冻结 99.删除)
		state?: number;
		// 角色ID集合
		roleIds?: string;
	};
}
