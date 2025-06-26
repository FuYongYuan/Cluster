package cn.fyy.builder.service;

import cn.fyy.builder.bean.bo.CommonlyVersionBO;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;

/**
 * 常用版本 Service
 *
 * @author fuyy
 */
public interface CommonlyVersionService {
    /**
     * 新增或者修改
     *
     * @param bo                 常用版本 BO
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录用户名
     * @return !=null 成功，==null 失败
     * @throws BusinessException 错误
     */
    ResultMessage<String> save(CommonlyVersionBO bo, Long currentManagerId, String currentManagerName) throws BusinessException;

    /**
     * 新增或者修改
     *
     * @param bo                 管理员 BO
     * @param currentManagerId   当前登陆人id
     * @param currentManagerName 当前登陆人名称
     * @param getNull            是否更新空
     * @return !=null 成功，==null 失败
     * @throws BusinessException 错误
     */
    CommonlyVersionBO save(CommonlyVersionBO bo, Long currentManagerId, String currentManagerName, boolean getNull) throws BusinessException;

    /**
     * 根据主键查询
     *
     * @param id 主键ID
     * @return 常用版本
     * @throws BusinessException 错误
     */
    CommonlyVersionBO getById(Long id) throws BusinessException;

    /**
     * 根据主键删除 主键可以是多个用,分割
     *
     * @param ids                删除主键 可以使用,分割
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录人名
     * @return 受影响行数
     * @throws BusinessException 删除错误,Exception
     */
    int updateDelete(String ids, Long currentManagerId, String currentManagerName) throws BusinessException;

    /**
     * 根据用户主键查询
     *
     * @param managerId 主键ID
     * @return 常用版本
     * @throws BusinessException 错误
     */
    CommonlyVersionBO getByManagerId(Long managerId) throws BusinessException;
}
