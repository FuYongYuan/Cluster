package cn.fyy.authorization.service.impl;

import cn.fyy.authorization.bean.bo.RoleBO;
import cn.fyy.authorization.bean.dbo.RoleDO;
import cn.fyy.authorization.feign.client.capability.MenuFeignClient;
import cn.fyy.authorization.repository.RoleRepository;
import cn.fyy.authorization.service.RoleMenuService;
import cn.fyy.authorization.service.RoleService;
import cn.fyy.capability.bean.dto.MenuDTO;
import cn.fyy.common.bean.ao.DataState;
import cn.fyy.common.bean.ao.OperateResult;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.common.service.ConstantService;
import dispose.CopyClass;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * 角色 Service
 *
 * @author fuyy
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    /**
     * 角色 Repository
     */
    @Resource
    private RoleRepository roleRepository;

    /**
     * 角色菜单 Service
     */
    @Resource
    private RoleMenuService roleMenuServiceImpl;

    //------------------------------------------------------------------------------------------------------------------feign
    /**
     * 角色关系 FeignClient
     */
    @Resource
    private MenuFeignClient menuFeignClient;

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
    @Transactional(rollbackFor = BusinessException.class)
    public ResultMessage<String> save(RoleBO bo, Long currentManagerId, String currentManagerName) throws BusinessException {
        try {
            RoleBO result = this.save(bo, currentManagerId, currentManagerName, false);
            if (result != null) {
                if (StringUtils.hasText(bo.getMenuIds())) {
                    ResultMessage<String> roleMenuSaveListResult = roleMenuServiceImpl.saveList(bo.getId(), bo.getMenuIds(), currentManagerId, currentManagerName);
                    if (roleMenuSaveListResult.getCode() != HttpStatus.OK.value()) {
                        throw new RuntimeException("新增或者修改角色菜单关系错误");
                    }
                }
                return new ResultMessage<>(OperateResult.SUCCESS.getMessage());
            } else {
                return new ResultMessage<>(1, OperateResult.FAIL.getMessage());
            }
        } catch (Exception e) {
            throw new BusinessException("新增或者修改角色错误", e);
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
    public RoleBO save(RoleBO bo, Long currentManagerId, String currentManagerName, boolean getNull) throws BusinessException {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            RoleDO dbo;
            if (bo.getId() == null) {
                bo.setCreatorId(currentManagerId);
                bo.setCreatorName(currentManagerName);
                bo.setCreateTime(localDateTime);
                bo.setUpdaterId(currentManagerId);
                bo.setUpdaterName(currentManagerName);
                bo.setUpdateTime(localDateTime);
                bo.setState(DataState.NORMAL.getCode());
                dbo = RoleBO.toDO(bo);
            } else {
                RoleDO old = roleRepository.getReferenceById(bo.getId());
                // 根据getNull复制其中的非空或包含空字段
                CopyClass.copyClassGetSet(bo, old, RoleDO.class, getNull);
                old.setUpdaterId(currentManagerId);
                old.setUpdaterName(currentManagerName);
                old.setUpdateTime(localDateTime);
                dbo = old;
            }

            return RoleBO.toBO(roleRepository.save(dbo));
        } catch (Exception e) {
            throw new BusinessException("新增或者修改角色错误", e);
        }
    }

    //------------------------------------------------------------------------------------------------------------------自定义方法

    /**
     * 根据名称查询角色列表
     *
     * @param currentPage  当前页
     * @param eachPageSize 每页多少条
     * @param pageSort     排序
     * @param roleName     角色名称
     * @param state        状态
     * @return TRoleDO 角色对象
     */
    @Override
    public Page<RoleBO> queryPageByRoleNameAndState(
            int currentPage,
            int eachPageSize,
            String pageSort,
            String roleName,
            Integer state
    ) throws BusinessException {
        try {
            // 查询拼装
            Specification<RoleDO> specification = (root, query, criteriaBuilder) -> {
                Predicate predicate;
                // 条件拼装
                predicate = criteriaBuilder.and(criteriaBuilder.equal(root.get("state"), Objects.requireNonNullElse(state, DataState.NORMAL.getCode())));
                if (StringUtils.hasText(roleName)) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("roleName"), "%" + roleName + "%"));
                }
                query.where(predicate);
                // 排序拼装
                query.orderBy(ConstantService.getSort(root, criteriaBuilder, pageSort));
                // 生成拼装结果
                return query.getRestriction();
            };
            // 分页信息
            Pageable pageable = PageRequest.of(currentPage, eachPageSize);
            // 执行查询
            Page<RoleDO> doPage = roleRepository.findAll(specification, pageable);
            // 返回结果 执行查询
            return new PageImpl<>(RoleBO.toBO(doPage.getContent()), doPage.getPageable(), doPage.getTotalElements());
        } catch (Exception e) {
            throw new BusinessException("根据名称查询角色列表错误", e);
        }
    }

    /**
     * 根据主键删除 主键可以是多个用,分割
     *
     * @param ids                删除主键 可以使用,分割
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录人名称
     * @return 受影响行数
     * @throws BusinessException 删除错误,Exception
     */
    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public int updateDelete(String ids, Long currentManagerId, String currentManagerName) throws BusinessException {
        try {
            if (StringUtils.hasText(ids)) {
                return roleRepository.updateStateByIds(DataState.DELETE.getCode(), currentManagerId, currentManagerName, LocalDateTime.now(), Stream.of(ids.split(",")).map(Long::valueOf).toList());
            } else {
                return 0;
            }
        } catch (Exception e) {
            throw new BusinessException("根据主键删除 主键可以是多个用,分割错误", e);
        }
    }

    /**
     * 根据主键查询
     *
     * @param id 主键ID
     * @return 角色
     */
    @Override
    public RoleBO getById(Long id) throws BusinessException {
        try {
            RoleBO bo = RoleBO.toBO(roleRepository.getReferenceById(id));
            if (bo != null) {
                List<Long> roleIdList = new ArrayList<>();
                roleIdList.add(bo.getId());
                List<Long> menuList = roleMenuServiceImpl.queryMenuIdsByRoleIds(roleIdList);
                ResultMessage<List<MenuDTO>> resultMessage = menuFeignClient.queryMenuByMenuIdList(menuList);
                if (resultMessage.getCode() == HttpStatus.OK.value()) {
                    List<String> menuIdList = resultMessage.getData().stream().map(r -> r.getId().toString()).toList();
                    bo.setMenuIds(String.join(",", menuIdList));
                }
            }
            return bo;
        } catch (Exception e) {
            throw new BusinessException("根据主键查询错误", e);
        }
    }

    /**
     * 查询角色列表
     *
     * @return TRoleBO 角色对象
     */
    @Override
    public List<RoleBO> queryAll(
    ) throws BusinessException {
        try {
            return RoleBO.toBO(
                    roleRepository.queryAll()
            );
        } catch (Exception e) {
            throw new BusinessException("查询角色列表错误", e);
        }
    }


    /**
     * 根据管理员主键ID查询能够使用的角色列表
     *
     * @param managerId 管理员主键ID
     * @return 能够使用的角色列表
     */
    @Override
    public List<RoleBO> queryManagerHaveRoleByManagerId(
            Long managerId
    ) throws BusinessException {
        try {
            return RoleBO.toBO(
                    roleRepository.queryManagerHaveRoleByManagerIdAndState(managerId, DataState.NORMAL.getCode())
            );
        } catch (Exception e) {
            throw new BusinessException("根据管理员主键ID查询能够使用的角色列表错误", e);
        }
    }

}
