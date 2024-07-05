import Request from "@/utils/axios/request";
import {MemberBaseURL, SuccessRequestCode} from "@/apis/commons/constant";
import {showErrorNotification} from "@/antd/notification";
import {ManagerDTO, PageImpl} from "DTO";


/**
 * 根据JwtToken查询
 *
 */
export const getManagerByJwtToken = (): Promise<ManagerDTO | undefined> => {
    // 初始化
    const request = new Request<ManagerDTO>();
    // 地址
    const url = (MemberBaseURL + "/manager/get/jwt/token");
    // 请求
    return request.get(
        url
    ).then((response) => {
        if (response.data.code === SuccessRequestCode) {
            return response.data.data;
        } else {
            showErrorNotification({
                message: "根据JwtToken查询错误",
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
export const getManager = (
    id: number
): Promise<ManagerDTO | undefined> => {
    // 初始化
    const request = new Request<ManagerDTO>();
    // 地址
    const url = (MemberBaseURL + "/manager/get/" + id);
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
export const saveManager = (
    dto: ManagerDTO
): Promise<string | undefined> => {
    // 初始化
    const request = new Request<string>();
    // 地址
    const url = (MemberBaseURL + "/manager/save");
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

/**
 * 根据账号、手机号码、邮箱、管理员名称、状态查询管理员列表
 *
 * @param currentPage 当前页
 * @param eachPageSize 每页多少条
 * @param pageSort 分页排序
 * @param account 账号
 * @param mobile 手机号码
 * @param mail 邮箱
 * @param managerName 管理员名称
 * @param state 状态
 */
export const queryManager = (
    currentPage: number,
    eachPageSize: number,
    pageSort?: string,
    account?: string,
    mobile?: string,
    mail?: string,
    managerName?: string,
    state?: number
): Promise<PageImpl<ManagerDTO> | undefined> => {
    // 初始化
    const request = new Request<PageImpl<ManagerDTO>>();
    // 参数
    const params = {
        pageSort,
        account,
        mobile,
        mail,
        managerName,
        state,
    }
    // 地址
    const url = (MemberBaseURL + "/manager/query/" + currentPage + "/" + eachPageSize);
    // 请求
    return request.get(
        url,
        params
    ).then((response) => {
        if (response.data.code === SuccessRequestCode) {
            return response.data.data;
        } else {
            showErrorNotification({
                message: "根据账号、手机号码、邮箱、管理员名称、状态查询管理员列表",
                description: response.data.message,
            });
        }
    });
};

/**
 * 删除
 * @param ids ID集合
 */
export const deleteManager = (
    ids: string
): Promise<number | undefined> => {
    // 初始化
    const request = new Request<number>();
    // 地址
    const url = (MemberBaseURL + "/manager/delete/" + ids);
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
 * 冻结
 * @param ids ID集合
 */
export const freezeManager = (
    ids: string
): Promise<number | undefined> => {
    return updateManagerState(ids, 1);
}

/**
 * 解冻
 * @param ids ID集合
 */
export const recoverFreezeManager = (
    ids: string
): Promise<number | undefined> => {
    return updateManagerState(ids, 0);
}

/**
 * 修改管理员状态
 * @param ids ID集合
 * @param state 状态
 */
export const updateManagerState = (
    ids: string,
    state: number
): Promise<number | undefined> => {
    // 初始化
    const request = new Request<number>();
    // 地址
    const url = (MemberBaseURL + "/manager/update/state/" + ids + "/" + state);
    // 请求
    return request.put(
        url
    ).then((response) => {
        if (response.data.code === SuccessRequestCode) {
            return response.data.data;
        } else {
            showErrorNotification({
                message: "修改管理员状态错误",
                description: response.data.message,
            });
        }
    });
}
