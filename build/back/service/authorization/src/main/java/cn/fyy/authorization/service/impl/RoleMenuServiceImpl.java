package cn.fyy.authorization.service.impl;

import cn.fyy.authorization.bean.bo.RoleMenuBO;
import cn.fyy.authorization.bean.dbo.RoleMenuDO;
import cn.fyy.authorization.repository.RoleMenuRepository;
import cn.fyy.authorization.service.RoleMenuService;
import cn.fyy.common.bean.ao.DataState;
import cn.fyy.common.bean.ao.OperateResult;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import dispose.CopyClass;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

/**
 * 角色菜单关系 Service
 *
 * @author fuyy
 */
@Slf4j
@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    /**
     * 角色菜单关系 Repository
     */
    @Resource
    private RoleMenuRepository roleMenuRepository;

    //------------------------------------------------------------------------------------------------------------------基础方法

    /**
     * 新增或者修改
     *
     * @param bo                 角色 BO
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录人名称
     * @return !=null 成功，==null 失败
     */
    @Override
    public ResultMessage<String> save(RoleMenuBO bo, BigInteger currentManagerId, String currentManagerName) throws BusinessException {
        try {
            RoleMenuBO result = this.save(bo, currentManagerId, currentManagerName, false);
            if (result != null) {
                return new ResultMessage<>(OperateResult.SUCCESS);
            } else {
                return new ResultMessage<>(1, OperateResult.FAIL);
            }
        } catch (Exception e) {
            throw new BusinessException("新增或者修改角色菜单关系错误", e);
        }
    }

    /**
     * 新增或者修改
     *
     * @param bo                 角色 BO
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录人名称
     * @param getNull            是否更新空
     * @return !=null 成功，==null 失败
     */
    @Override
    public RoleMenuBO save(RoleMenuBO bo, BigInteger currentManagerId, String currentManagerName, boolean getNull) throws BusinessException {
        try {
            Date date = new Date();
            RoleMenuDO dbo;
            if (bo.getId() == null) {
                bo.setCreatorId(currentManagerId);
                bo.setCreatorName(currentManagerName);
                bo.setCreateTime(date);
                bo.setUpdaterId(currentManagerId);
                bo.setUpdaterName(currentManagerName);
                bo.setUpdateTime(date);
                bo.setState(DataState.NORMAL);
                dbo = RoleMenuBO.toDO(bo);
            } else {
                RoleMenuDO old = roleMenuRepository.getReferenceById(bo.getId());
                // 根据getNull复制其中的非空或包含空字段
                CopyClass.copyClassGetSet(bo, old, RoleMenuDO.class, getNull);
                old.setUpdaterId(currentManagerId);
                old.setUpdaterName(currentManagerName);
                old.setUpdateTime(date);
                dbo = old;
            }

            return RoleMenuBO.toBO(roleMenuRepository.save(dbo));
        } catch (Exception e) {
            throw new BusinessException("新增或者修改角色菜单关系错误", e);
        }
    }

    //------------------------------------------------------------------------------------------------------------------自定义方法

    /**
     * 保存集合
     *
     * @param menuIds            菜单主键ID集合
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录人名称
     * @return 是否成功
     */
    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResultMessage<String> saveList(BigInteger roleId, String menuIds, BigInteger currentManagerId, String currentManagerName) throws BusinessException {
        try {
            Date date = new Date();
            if (StringUtils.hasText(menuIds)) {
                // 删除原先的角色菜单关系
                roleMenuRepository.deleteByRoleId(roleId);
                // 新增角色菜单关系
                List<BigInteger> menuId = Stream.of(menuIds.split(",")).map(BigInteger::new).toList();
                List<RoleMenuDO> list = new ArrayList<>();
                for (BigInteger id : menuId) {
                    RoleMenuDO bo = RoleMenuDO.builder()
                            .roleId(roleId)
                            .menuId(id)
                            .creatorId(currentManagerId)
                            .creatorName(currentManagerName)
                            .createTime(date)
                            .updaterId(currentManagerId)
                            .updaterName(currentManagerName)
                            .updateTime(date)
                            .state(DataState.NORMAL)
                            .build();
                    list.add(bo);
                }
                List<RoleMenuBO> tRoleMenuBOList = RoleMenuBO.toBO(roleMenuRepository.saveAll(list));
                if (!tRoleMenuBOList.isEmpty()) {
                    return new ResultMessage<>(OperateResult.SUCCESS);
                }
            } else {
                return new ResultMessage<>(1, OperateResult.FAIL);
            }
        } catch (Exception e) {
            throw new BusinessException("新增或者修改角色菜单关系错误", e);
        }
        return null;
    }


    /**
     * 根据角色查询现有菜单关系列表
     *
     * @param roleId 角色主键ID
     * @return 现有菜单关系列表
     */
    @Override
    public List<RoleMenuBO> queryByRoleIdOrderByUpdateTimeAsc(BigInteger roleId) throws BusinessException {
        try {
            return RoleMenuBO.toBO(roleMenuRepository.queryByRoleIdOrderByUpdateTimeAsc(roleId));
        } catch (Exception e) {
            throw new BusinessException("根据角色查询现有菜单关系列表错误", e);
        }
    }


    /**
     * 根据现有管理员拥有的角色查询所拥有的菜单ID
     *
     * @param roleIds 角色主键ID
     * @return 现有菜单关系列表
     */
    @Override
    public List<BigInteger> queryMenuIdsByRoleIds(List<BigInteger> roleIds) throws BusinessException {
        try {
            List<RoleMenuBO> roleMenuBOList = RoleMenuBO.toBO(roleMenuRepository.queryByRoleIdsAndState(roleIds, DataState.NORMAL));
            if (roleMenuBOList.isEmpty()) {
                return null;
            } else {
                List<BigInteger> menuList = new ArrayList<>();

                roleMenuBOList.forEach(bo -> menuList.add(bo.getMenuId()));

                return menuList;
            }
        } catch (Exception e) {
            throw new BusinessException("根据现有管理员拥有的角色查询所拥有的菜单ID错误", e);
        }
    }
}