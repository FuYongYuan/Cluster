package cn.fyy.authorization.service;

import cn.fyy.authorization.bean.bo.RoleBO;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import org.springframework.data.domain.Page;

import java.math.BigInteger;
import java.util.List;

/**
 * 角色 Service
 *
 * @author fuyy
 */
public interface RoleService {
    /**
     * 新增或者修改
     *
     * @param bo                 角色 BO
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录用户名称
     * @return !=null 成功，==null 失败
     * @throws BusinessException 错误
     */
    ResultMessage<String> save(RoleBO bo, BigInteger currentManagerId, String currentManagerName) throws BusinessException;

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
    RoleBO save(RoleBO bo, BigInteger currentManagerId, String currentManagerName, boolean getNull) throws BusinessException;

    /**
     * 根据名称查询角色列表
     *
     * @param currentPage  当前页
     * @param eachPageSize 每页多少条
     * @param pageSort     排序
     * @param roleName     角色名称
     * @param state        状态
     * @return TRoleDO 角色对象
     * @throws BusinessException 错误
     */
    Page<RoleBO> queryPageByRoleNameAndState(
            int currentPage,
            int eachPageSize,
            String pageSort,
            String roleName,
            Integer state
    ) throws BusinessException;

    /**
     * 根据主键删除 主键可以是多个用,分割
     *
     * @param ids                删除主键 可以使用,分割
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录人名称
     * @return 受影响行数
     * @throws BusinessException 删除错误,Exception
     */
    int updateDelete(String ids, BigInteger currentManagerId, String currentManagerName) throws BusinessException;

    /**
     * 根据主键查询
     *
     * @param id 主键ID
     * @return 角色
     * @throws BusinessException 错误
     */
    RoleBO getById(BigInteger id) throws BusinessException;

    /**
     * 查询角色列表
     *
     * @return TRoleBO 角色对象
     * @throws BusinessException 错误
     */
    List<RoleBO> queryAll(
    ) throws BusinessException;

    /**
     * 根据管理员主键ID查询能够使用的角色列表
     *
     * @param managerId 管理员主键ID
     * @return 能够使用的角色列表
     * @throws BusinessException 错误
     */
    List<RoleBO> queryManagerHaveRoleByManagerId(
            BigInteger managerId
    ) throws BusinessException;
}
