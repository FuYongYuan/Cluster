package cn.fyy.authorization.service.impl;

import cn.fyy.authorization.bean.bo.RoleMenuBO;
import cn.fyy.authorization.bean.po.RoleMenuPO;
import cn.fyy.authorization.repository.RoleMenuRepository;
import cn.fyy.authorization.service.RoleMenuService;
import cn.fyy.common.bean.ao.OperateResult;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.database.util.BeanUtil;
import cn.fyy.database.util.snowflake.SnowflakeIdUtil;
import cn.fyy.jpa.bean.ao.DataState;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * 角色菜单关系 Service
 *
 * @author fyy
 */
@Slf4j
@Service
public class RoleMenuServiceImpl implements RoleMenuService {
    /**
     * 雪花算法
     */
    @Resource
    private SnowflakeIdUtil snowflakeIdUtil;

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
     * @param currentManagerId   当前登录人 ID
     * @param currentManagerName 当前登录人名称
     * @return !=null 成功，==null 失败
     */
    @Override
    public ResultMessage<String> save(RoleMenuBO bo, Long currentManagerId, String currentManagerName) throws BusinessException {
        try {
            RoleMenuBO result = this.save(bo, currentManagerId, currentManagerName, false);
            if (result != null) {
                return new ResultMessage<>(OperateResult.SUCCESS.getMessage());
            } else {
                return new ResultMessage<>(1, OperateResult.FAIL.getMessage());
            }
        } catch (Exception e) {
            throw new BusinessException("新增或者修改角色菜单关系错误", e);
        }
    }

    /**
     * 新增或者修改
     *
     * @param bo                 角色 BO
     * @param currentManagerId   当前登录人 ID
     * @param currentManagerName 当前登录人名称
     * @param getNull            是否更新空
     * @return !=null 成功，==null 失败
     */
    @Override
    public RoleMenuBO save(RoleMenuBO bo, Long currentManagerId, String currentManagerName, boolean getNull) throws BusinessException {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            RoleMenuPO po;
            if (bo.getId() == null) {
                po = BeanUtil.insert(
                        RoleMenuBO.toPO(bo),
                        snowflakeIdUtil.getGenerator().nextId(),
                        currentManagerId,
                        currentManagerName,
                        localDateTime
                );
            } else {
                RoleMenuPO old = roleMenuRepository.getReferenceById(bo.getId());
                // 根据 getNull 复制其中的非空或包含空字段
                BeanUtil.copyProperties(bo, old, getNull);
                po = BeanUtil.update(
                        old,
                        currentManagerId,
                        currentManagerName,
                        localDateTime
                );
            }

            return RoleMenuBO.toBO(roleMenuRepository.save(po));
        } catch (Exception e) {
            throw new BusinessException("新增或者修改角色菜单关系错误", e);
        }
    }

    //------------------------------------------------------------------------------------------------------------------自定义方法

    /**
     * 保存集合
     *
     * @param menuIds            菜单主键 ID 集合
     * @param currentManagerId   当前登录人 ID
     * @param currentManagerName 当前登录人名称
     * @return 是否成功
     */
    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public ResultMessage<String> saveList(Long roleId, String menuIds, Long currentManagerId, String currentManagerName) throws BusinessException {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            if (StringUtils.hasText(menuIds)) {
                // 删除原先的角色菜单关系
                roleMenuRepository.deleteByRoleId(roleId);
                // 新增角色菜单关系
                List<Long> menuId = Stream.of(menuIds.split(",")).map(Long::valueOf).toList();
                List<RoleMenuPO> list = new ArrayList<>();
                for (Long id : menuId) {
                    RoleMenuPO bo = RoleMenuPO.builder()
                            .roleId(roleId)
                            .menuId(id)
                            .creatorId(currentManagerId)
                            .creatorName(currentManagerName)
                            .createTime(localDateTime)
                            .updaterId(currentManagerId)
                            .updaterName(currentManagerName)
                            .updateTime(localDateTime)
                            .state(DataState.NORMAL.getCode())
                            .build();
                    list.add(bo);
                }
                List<RoleMenuBO> tRoleMenuBOList = RoleMenuBO.toBO(roleMenuRepository.saveAll(list));
                if (!tRoleMenuBOList.isEmpty()) {
                    return new ResultMessage<>(OperateResult.SUCCESS.getMessage());
                }
            } else {
                return new ResultMessage<>(1, OperateResult.FAIL.getMessage());
            }
        } catch (Exception e) {
            throw new BusinessException("新增或者修改角色菜单关系错误", e);
        }
        return null;
    }


    /**
     * 根据角色查询现有菜单关系列表
     *
     * @param roleId 角色主键 ID
     * @return 现有菜单关系列表
     */
    @Override
    public List<RoleMenuBO> queryByRoleIdOrderByUpdateTimeAsc(Long roleId) throws BusinessException {
        try {
            return RoleMenuBO.toBO(roleMenuRepository.queryByRoleIdOrderByUpdateTimeAsc(roleId));
        } catch (Exception e) {
            throw new BusinessException("根据角色查询现有菜单关系列表错误", e);
        }
    }


    /**
     * 根据现有管理员拥有的角色查询所拥有的菜单 ID
     *
     * @param roleIds 角色主键 ID
     * @return 现有菜单关系列表
     */
    @Override
    public List<Long> queryMenuIdsByRoleIds(List<Long> roleIds) throws BusinessException {
        try {
            List<RoleMenuBO> roleMenuBOList = RoleMenuBO.toBO(roleMenuRepository.queryByRoleIdsAndState(roleIds, DataState.NORMAL.getCode()));
            if (roleMenuBOList.isEmpty()) {
                return null;
            } else {
                List<Long> menuList = new ArrayList<>();

                roleMenuBOList.forEach(bo -> menuList.add(bo.getMenuId()));

                return menuList;
            }
        } catch (Exception e) {
            throw new BusinessException("根据现有管理员拥有的角色查询所拥有的菜单 ID 错误", e);
        }
    }

    /**
     * 根据现有管理员拥有的角色查询所拥有的菜单
     *
     * @param roleIds 角色主键 ID
     * @return 现有菜单关系列表
     */
    @Override
    public List<RoleMenuBO> queryMenuByRoleIds(List<Long> roleIds) throws BusinessException {
        try {
            return RoleMenuBO.toBO(roleMenuRepository.queryByRoleIdsAndState(roleIds, DataState.NORMAL.getCode()));
        } catch (Exception e) {
            throw new BusinessException("根据现有管理员拥有的角色查询所拥有的菜单 ID 错误", e);
        }
    }
}
