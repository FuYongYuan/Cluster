import Request from "@/utils/axios/request";
import {
    AuthorizationBaseURL,
    LocalStorageAccount,
    LocalStorageAccountMenuList,
    LocalStorageHeadImgUrl,
    LocalStorageInvalidDate,
    LocalStorageJwtToken,
    LocalStorageManagerName,
    SuccessRequestCode,
} from "@/apis/commons/constant";
import {showErrorNotification} from "@/antd/notification";
import {JwtDTO, MenuDTO, PageImpl, RoleDTO} from "DTO";

//----------------------------------------------------------------------------------------------------------------------账号管理
/**
 * 登录
 * @param account 账号
 * @param loginPassword 密码
 * @param imageCaptcha 图形验证码
 * @param imageCaptchaCacheName 图形验证码缓存名称
 */
export const login = (
    account: string,
    loginPassword: string,
    imageCaptcha: string,
    imageCaptchaCacheName: string
): Promise<JwtDTO | undefined> => {
    const request = new Request<JwtDTO>();
    return request
        .post(
            AuthorizationBaseURL +
            "/system/login/" +
            account +
            "/" +
            loginPassword +
            "/" +
            imageCaptcha +
            "/" +
            imageCaptchaCacheName
        )
        .then((response) => {
            if (response.data.code === SuccessRequestCode) {
                return response.data.data;
            } else {
                showErrorNotification({
                    message: "登录失败",
                    description: response.data.message,
                });
            }
        });
};

/**
 * 登出
 */
export const logout = () => {
    const request = new Request<string>();
    return request.post(AuthorizationBaseURL + "/system/logout").then((response) => {
        if (response.data.data) {
            localStorage.removeItem(LocalStorageAccount);
            localStorage.removeItem(LocalStorageHeadImgUrl);
            localStorage.removeItem(LocalStorageManagerName);
            localStorage.removeItem(LocalStorageJwtToken);
            localStorage.removeItem(LocalStorageInvalidDate);
            localStorage.removeItem(LocalStorageAccountMenuList);
            return true;
        } else {
            showErrorNotification({
                message: "登出失败",
                description: response.data.message,
            });
            return false;
        }
    });
};

/**
 * 注册
 * @param mail 邮箱
 * @param account 账号
 * @param loginPassword 密码
 * @param mailCaptcha 邮件验证码
 * @param mailCaptchaCacheName 邮件验证码缓存名称
 */
export const register = (
    mail: string,
    account: string,
    loginPassword: string,
    mailCaptcha: string,
    mailCaptchaCacheName: string
): Promise<JwtDTO | undefined> => {
    const request = new Request<JwtDTO>();
    return request
        .post(
            AuthorizationBaseURL +
            "/system/register/" +
            mail +
            "/" +
            account +
            "/" +
            loginPassword +
            "/" +
            mailCaptcha +
            "/" +
            mailCaptchaCacheName
        )
        .then((response) => {
            if (response.data.code === SuccessRequestCode) {
                return response.data.data;
            } else {
                showErrorNotification({
                    message: "注册失败",
                    description: response.data.message,
                });
            }
        });
};

/**
 * 找回密码
 * @param mail 邮箱
 * @param loginPassword 密码
 * @param mailCaptcha 邮件验证码
 * @param mailCaptchaCacheName 邮件验证码缓存名称
 */
export const recover = (
    mail: string,
    loginPassword: string,
    mailCaptcha: string,
    mailCaptchaCacheName: string
): Promise<JwtDTO | undefined> => {
    const request = new Request<JwtDTO>();
    return request
        .put(
            AuthorizationBaseURL +
            "/system/recover/" +
            mail +
            "/" +
            loginPassword +
            "/" +
            mailCaptcha +
            "/" +
            mailCaptchaCacheName
        )
        .then((response) => {
            if (response.data.code === SuccessRequestCode) {
                return response.data.data;
            } else {
                showErrorNotification({
                    message: "找回密码失败",
                    description: response.data.message,
                });
            }
        });
};

/**
 * 查询拥有的菜单权限
 */
export const queryHaveMenu = (): Promise<MenuDTO[] | undefined> => {
    const request = new Request<MenuDTO[]>();
    return request
        .get(
            AuthorizationBaseURL +
            "/system/query/manager/have/menu"
        )
        .then((response) => {
            if (response.data.code === SuccessRequestCode) {
                return response.data.data;
            } else {
                showErrorNotification({
                    message: "查询拥有的菜单权限错误",
                    description: response.data.message,
                });
            }
        });
};

/**
 * 查询所有角色
 */
export const queryRoleAll = (): Promise<RoleDTO[] | undefined> => {
    const request = new Request<RoleDTO[]>();
    return request
        .get(
            AuthorizationBaseURL +
            "/role/query/all"
        )
        .then((response) => {
            if (response.data.code === SuccessRequestCode) {
                return response.data.data;
            } else {
                showErrorNotification({
                    message: "查询所有角色错误",
                    description: response.data.message,
                });
            }
        });
};

//----------------------------------------------------------------------------------------------------------------------角色管理
/**
 * 根据角色名称、状态查询参数列表
 *
 * @param currentPage 当前页
 * @param eachPageSize 每页多少条
 * @param pageSort 分页排序
 * @param roleName 角色名称
 * @param state 状态
 */
export const queryRole = (
    currentPage: number,
    eachPageSize: number,
    pageSort?: string,
    roleName?: string,
    state?: number
): Promise<PageImpl<RoleDTO> | undefined> => {
    // 初始化
    const request = new Request<PageImpl<RoleDTO>>();
    // 参数
    const params = {
        pageSort,
        roleName,
        state,
    }
    // 地址
    const url = (AuthorizationBaseURL + "/role/query/" + currentPage + "/" + eachPageSize);
    // 请求
    return request.get(
        url,
        params
    ).then((response) => {
        if (response.data.code === SuccessRequestCode) {
            return response.data.data;
        } else {
            showErrorNotification({
                message: "根据角色名称、状态查询参数列表错误",
                description: response.data.message,
            });
        }
    });
};

/**
 * 删除
 * @param ids ID集合
 */
export const deleteRole = (
    ids: string
): Promise<number | undefined> => {
    // 初始化
    const request = new Request<number>();
    // 地址
    const url = (AuthorizationBaseURL + "/role/delete/" + ids);
    // 请求
    return request.delete(
        url
    ).then((response) => {
        if (response.data.code === SuccessRequestCode) {
            return response.data.data;
        } else {
            showErrorNotification({
                message: "删除错误",
                description: response.data.message,
            });
        }
    });
}


/**
 * 根据Id查询
 *
 * @param id ID
 */
export const getRole = (
    id: number
): Promise<RoleDTO | undefined> => {
    // 初始化
    const request = new Request<RoleDTO>();
    // 地址
    const url = (AuthorizationBaseURL + "/role/get/" + id);
    // 请求
    return request.get(
        url
    ).then((response) => {
        if (response.data.code === SuccessRequestCode) {
            return response.data.data;
        } else {
            showErrorNotification({
                message: "根据Id查询错误",
                description: response.data.message,
            });
        }
    });
}

/**
 * 保存
 *
 * @param dto DTO
 */
export const saveRole = (
    dto: RoleDTO
): Promise<string | undefined> => {
    // 初始化
    const request = new Request<string>();
    // 地址
    const url = (AuthorizationBaseURL + "/role/save");
    // 请求
    return request.post(
        url,
        dto
    ).then((response) => {
        if (response.data.code === SuccessRequestCode) {
            return response.data.data;
        } else {
            showErrorNotification({
                message: "保存错误",
                description: response.data.message,
            });
        }
    });
}
