package cn.fyy.capability.service;

import cn.fyy.capability.bean.bo.ApiBO;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;

import java.util.List;

/**
 * Api 信息 Service
 *
 * @author fyy
 */
public interface ApiService {
    /**
     * 新增或者修改
     *
     * @param bo                 Api 信息 BO
     * @param currentManagerId   当前登录人 ID
     * @param currentManagerName 当前登录用户名
     * @return !=null 成功，==null 失败
     * @throws BusinessException 错误
     */
    ResultMessage<String> save(ApiBO bo, Long currentManagerId, String currentManagerName) throws BusinessException;

    /**
     * 新增或者修改
     *
     * @param bo                 管理员 BO
     * @param currentManagerId   当前登陆人 ID
     * @param currentManagerName 当前登陆人名称
     * @param getNull            是否更新空
     * @return !=null 成功，==null 失败
     * @throws BusinessException 错误
     */
    ApiBO save(ApiBO bo, Long currentManagerId, String currentManagerName, boolean getNull) throws BusinessException;

    /**
     * 新增或者修改集合
     *
     * @param bo             Api 信息 BO
     * @param authentication 鉴权串
     * @return !=null 成功，==null 失败
     * @throws BusinessException 错误
     */
    ResultMessage<String> saveList(List<ApiBO> bo, String authentication) throws BusinessException;

    /**
     * 新增或者修改集合
     *
     * @param bo                 Api 信息 BO
     * @param currentManagerId   当前登录人 ID
     * @param currentManagerName 当前登录用户名
     * @return !=null 成功，==null 失败
     * @throws BusinessException 错误
     */
    ResultMessage<String> saveList(List<ApiBO> bo, Long currentManagerId, String currentManagerName, boolean getNull) throws BusinessException;
}
