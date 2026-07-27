package cn.fyy.authorization.service.impl;

import cn.fyy.authorization.bean.bo.RoleBO;
import cn.fyy.authorization.bean.po.RolePO;
import cn.fyy.authorization.config.properties.AesProperties;
import cn.fyy.authorization.feign.client.capability.ApiFeignClient;
import cn.fyy.authorization.feign.client.capability.MenuFeignClient;
import cn.fyy.authorization.repository.RoleRepository;
import cn.fyy.authorization.service.RoleApiService;
import cn.fyy.authorization.service.RoleMenuService;
import cn.fyy.authorization.service.RoleService;
import cn.fyy.capability.bean.dto.ApiDTO;
import cn.fyy.capability.bean.dto.MenuDTO;
import cn.fyy.common.bean.ao.OperateResult;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.database.util.BeanUtil;
import cn.fyy.database.util.SelectUtil;
import cn.fyy.database.util.snowflake.SnowflakeIdUtil;
import cn.fyy.jpa.bean.ao.DataState;
import encrypt.AesUtil;
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
import java.util.stream.Stream;

/**
 * 角色 Service
 *
 * @author fyy
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {
    /**
     * 雪花算法
     */
    @Resource
    private SnowflakeIdUtil snowflakeIdUtil;

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

    /**
     * 角色API关系 Service
     */
    @Resource
    private RoleApiService roleApiServiceImpl;

    //------------------------------------------------------------------------------------------------------------------feign
    /**
     * 菜单 FeignClient
     */
    @Resource
    private MenuFeignClient menuFeignClient;

    /**
     * API信息 FeignClient
     */
    @Resource
    private ApiFeignClient apiFeignClient;

    //------------------------------------------------------------------------------------------------------------------越鉴权处理加密信息

    /**
     * aes 加密信息
     */
    @Resource
    private AesProperties aesProperties;

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
                if (StringUtils.hasText(bo.getApiIds())) {
                    ResultMessage<String> roleApiSaveListResult = roleApiServiceImpl.saveList(bo.getId(), bo.getApiIds(), currentManagerId, currentManagerName);
                    if (roleApiSaveListResult.getCode() != HttpStatus.OK.value()) {
                        throw new RuntimeException("新增或者修改角色API关系错误");
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
     * @param currentManagerId   当前登录人 ID
     * @param currentManagerName 当前登录人名称
     * @param getNull            是否更新空
     * @return !=null 成功，==null 失败
     */
    @Override
    public RoleBO save(RoleBO bo, Long currentManagerId, String currentManagerName, boolean getNull) throws BusinessException {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            RolePO po;
            if (bo.getId() == null) {
                po = BeanUtil.insert(
                        RoleBO.toPO(bo),
                        snowflakeIdUtil.getGenerator().nextId(),
                        currentManagerId,
                        currentManagerName,
                        localDateTime
                );
            } else {
                RolePO old = roleRepository.getReferenceById(bo.getId());
                // 根据 getNull 复制其中的非空或包含空字段
                BeanUtil.copyProperties(bo, old, getNull);
                po = BeanUtil.update(
                        old,
                        currentManagerId,
                        currentManagerName,
                        localDateTime
                );
            }

            return RoleBO.toBO(roleRepository.save(po));
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
     * @return TRolePO 角色对象
     */
    @Override
    public Page<RoleBO> queryPageByRoleNameAndState(
            int currentPage,
            int eachPageSize,
            String pageSort,
            String roleName,
            Byte state
    ) throws BusinessException {
        try {
            // 查询拼装
            Specification<RolePO> specification = (root, query, criteriaBuilder) -> {
                // 条件拼装
                Predicate predicate = SelectUtil.getPredicate(root, criteriaBuilder, state);
                // 其他条件拼装
                if (StringUtils.hasText(roleName)) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("roleName"), "%" + roleName + "%"));
                }
                if (query != null) {
                    query.where(predicate);
                    // 排序拼装
                    query.orderBy(SelectUtil.getSort(root, criteriaBuilder, pageSort));
                    // 生成拼装结果
                    return query.getRestriction();
                } else {
                    return predicate;
                }
            };
            // 分页信息
            Pageable pageable = PageRequest.of(currentPage, eachPageSize);
            // 执行查询
            Page<RolePO> doPage = roleRepository.findAll(specification, pageable);
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
     * @param currentManagerId   当前登录人 ID
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
     * @param id 主键 ID
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
                if (menuList != null && !menuList.isEmpty()) {
                    String menuEncryptString = String.valueOf(menuList.size());
                    String menuEncrypt = AesUtil.encryptString(menuEncryptString, aesProperties.getAesKey());
                    ResultMessage<List<MenuDTO>> menuResultMessage = menuFeignClient.feignQueryMenuByMenuIdList(menuList, menuEncrypt);
                    if (menuResultMessage.getCode() == HttpStatus.OK.value()) {
                        List<String> menuIdList = menuResultMessage.getData().stream().map(r -> r.getId().toString()).toList();
                        bo.setMenuIds(String.join(",", menuIdList));
                    }
                }

                List<Long> apiList = roleApiServiceImpl.queryApiIdsByRoleIds(roleIdList);
                if (apiList != null && !apiList.isEmpty()) {
                    String apiEncryptString = String.valueOf(apiList.size());
                    String apiEncrypt = AesUtil.encryptString(apiEncryptString, aesProperties.getAesKey());
                    ResultMessage<List<ApiDTO>> apiResultMessage = apiFeignClient.feignQueryApiByApiIdList(apiList, apiEncrypt);
                    if (apiResultMessage.getCode() == HttpStatus.OK.value()) {
                        List<String> apiIdList = apiResultMessage.getData().stream().map(r -> r.getId().toString()).toList();
                        bo.setApiIds(String.join(",", apiIdList));
                    }
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
     * 根据管理员主键 ID 查询能够使用的角色列表
     *
     * @param managerId 管理员主键 ID
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
            throw new BusinessException("根据管理员主键 ID 查询能够使用的角色列表错误", e);
        }
    }

}
