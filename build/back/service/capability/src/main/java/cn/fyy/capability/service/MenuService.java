package cn.fyy.capability.service;

import cn.fyy.capability.bean.bo.MenuBO;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import org.springframework.data.domain.Page;

import java.math.BigInteger;
import java.util.List;

/**
 * 菜单 Service
 *
 * @author fuyy
 */
public interface MenuService {
    /**
     * 新增或者修改
     *
     * @param bo                 菜单 BO
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录人名称
     * @return !=null 成功，==null 失败
     * @throws BusinessException 错误
     */
    ResultMessage<String> save(MenuBO bo, BigInteger currentManagerId, String currentManagerName) throws BusinessException;

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
    MenuBO save(MenuBO bo, BigInteger currentManagerId, String currentManagerName, boolean getNull) throws BusinessException;

    /**
     * 根据名称查询菜单列表
     *
     * @param currentPage  当前页
     * @param eachPageSize 每页多少条
     * @param pageSort     排序
     * @param menuName     菜单名称
     * @param state        状态
     * @return MenuDO 菜单对象
     * @throws BusinessException 错误
     */
    Page<MenuBO> queryByMenuNameAndState(
            int currentPage,
            int eachPageSize,
            String pageSort,
            String menuName,
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
     * @return 菜单
     * @throws BusinessException 错误
     */
    MenuBO getById(BigInteger id) throws BusinessException;

    /**
     * 查询菜单列表
     *
     * @return MenuBO 菜单对象
     * @throws BusinessException 错误
     */
    List<MenuBO> queryAll(
    ) throws BusinessException;

    /**
     * 查询菜单列表
     *
     * @return MenuBO 菜单对象
     * @throws BusinessException 错误
     */
    List<MenuBO> queryAllParent(
    ) throws BusinessException;

    /**
     * 查询角色持有菜单列表
     *
     * @param menuIdList 角色ID集合
     * @return List<MenuBO> 菜单对象集合
     * @throws BusinessException 业务错误
     */
    List<MenuBO> queryHierarchyMenuByMenuIdList(
            List<BigInteger> menuIdList
    ) throws BusinessException;

    /**
     * 根据管理员主键ID查询能够使用的菜单列表
     *
     * @param menuIds 菜单ID集合
     * @return 能够使用的菜单列表
     * @throws BusinessException 业务错误
     */
    List<MenuBO> queryMenuByMenuIdList(
            List<BigInteger> menuIds
    ) throws BusinessException;
}
