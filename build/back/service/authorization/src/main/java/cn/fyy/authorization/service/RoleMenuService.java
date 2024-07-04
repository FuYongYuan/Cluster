package cn.fyy.authorization.service;

import cn.fyy.authorization.bean.bo.RoleMenuBO;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;

import java.math.BigInteger;
import java.util.List;

/**
 * 角色菜单 Service
 *
 * @author fuyy
 */
public interface RoleMenuService {
    /**
     * 新增或者修改
     *
     * @param bo                 角色 BO
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录用户名
     * @return !=null 成功，==null 失败
     * @throws BusinessException 错误
     */
    ResultMessage<String> save(RoleMenuBO bo, BigInteger currentManagerId, String currentManagerName) throws BusinessException;

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
    RoleMenuBO save(RoleMenuBO bo, BigInteger currentManagerId, String currentManagerName, boolean getNull) throws BusinessException;

    /**
     * 保存集合
     *
     * @param roleId             角色id
     * @param menuIds            菜单主键ID集合
     * @param currentManagerId   当前登录人
     * @param currentManagerName 当前登录人名称
     * @return 是否成功
     * @throws BusinessException 错误
     */
    ResultMessage<String> saveList(BigInteger roleId, String menuIds, BigInteger currentManagerId, String currentManagerName) throws BusinessException;

    /**
     * 根据角色查询现有菜单关系列表
     *
     * @param roleId 角色主键ID
     * @return 现有菜单关系列表
     * @throws BusinessException 错误
     */
    List<RoleMenuBO> queryByRoleIdOrderByUpdateTimeAsc(BigInteger roleId) throws BusinessException;

    /**
     * 根据现有管理员拥有的角色查询所拥有的菜单ID
     *
     * @param roleIds 角色主键ID
     * @return 现有菜单关系列表
     * @throws BusinessException 错误
     */
    List<BigInteger> queryMenuIdsByRoleIds(List<BigInteger> roleIds) throws BusinessException;

}
