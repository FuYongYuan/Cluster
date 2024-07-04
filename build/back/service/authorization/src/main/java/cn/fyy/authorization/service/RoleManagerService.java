package cn.fyy.authorization.service;

import cn.fyy.authorization.bean.bo.RoleManagerBO;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;

import java.math.BigInteger;

/**
 * 用户角色 Service
 *
 * @author fuyy
 */
public interface RoleManagerService {
    /**
     * 新增或者修改
     *
     * @param bo                 表_用户角色关系 BO
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录用户名
     * @return !=null 成功，==null 失败
     * @throws BusinessException 错误
     */
    ResultMessage<String> save(RoleManagerBO bo, BigInteger currentManagerId, String currentManagerName) throws BusinessException;

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
    RoleManagerBO save(RoleManagerBO bo, BigInteger currentManagerId, String currentManagerName, boolean getNull) throws BusinessException;

    /**
     * 保存集合
     *
     * @param managerId          管理员主键ID
     * @param roleIds            角色主键ID集合
     * @param currentManagerId   当前登录人
     * @param currentManagerName 当前登录人名称
     * @return 是否成功
     * @throws BusinessException 错误
     */
    ResultMessage<String> saveList(BigInteger managerId, String roleIds, BigInteger currentManagerId, String currentManagerName) throws BusinessException;

}
