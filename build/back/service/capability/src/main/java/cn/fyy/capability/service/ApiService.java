package cn.fyy.capability.service;

import cn.fyy.capability.bean.bo.ApiBO;
import cn.fyy.capability.bean.dto.ApiDTO;
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

    /**
     * 查询全部 Api 信息
     *
     * @return Api 信息集合
     * @throws BusinessException 错误
     */
    List<ApiBO> queryAll() throws BusinessException;

    /**
     * 根据管理员主键 ID 查询能够使用的API信息列表
     *
     * @param apiIds         API ID 集合
     * @param authentication 鉴权串
     * @return 能够使用的API信息列表
     */
    ResultMessage<List<ApiDTO>> feignQueryApiByApiIdList(
            List<Long> apiIds,
            String authentication
    ) throws BusinessException;
}
