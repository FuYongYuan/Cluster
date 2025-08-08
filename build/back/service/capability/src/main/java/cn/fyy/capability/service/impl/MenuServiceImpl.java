package cn.fyy.capability.service.impl;

import cn.fyy.capability.bean.bo.MenuBO;
import cn.fyy.capability.bean.po.MenuPO;
import cn.fyy.capability.repository.MenuRepository;
import cn.fyy.capability.service.MenuService;
import cn.fyy.common.bean.ao.DataState;
import cn.fyy.common.bean.ao.OperateResult;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.common.util.BeanUtil;
import cn.fyy.common.util.PageUtil;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * 菜单 Service
 *
 * @author fuyy
 */
@Slf4j
@Service
public class MenuServiceImpl implements MenuService {

    /**
     * 菜单 Repository
     */
    @Resource
    private MenuRepository menuRepository;

    //------------------------------------------------------------------------------------------------------------------基础方法

    /**
     * 新增或者修改
     *
     * @param bo                 菜单 BO
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录人名称
     * @return !=null 成功，==null 失败
     */
    @Override
    public ResultMessage<String> save(MenuBO bo, Long currentManagerId, String currentManagerName) throws BusinessException {
        try {
            MenuBO result = this.save(bo, currentManagerId, currentManagerName, false);
            if (result != null) {
                return new ResultMessage<>(OperateResult.SUCCESS.getMessage());
            } else {
                return new ResultMessage<>(1, OperateResult.FAIL.getMessage());
            }
        } catch (Exception e) {
            throw new BusinessException("新增或者修改菜单错误", e);
        }
    }

    /**
     * 新增或者修改
     *
     * @param bo                 菜单 BO
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录人名称
     * @param getNull            是否更新空
     * @return !=null 成功，==null 失败
     */
    @Override
    public MenuBO save(MenuBO bo, Long currentManagerId, String currentManagerName, boolean getNull) throws BusinessException {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            MenuPO po;
            if (bo.getId() == null) {
                bo.setCreatorId(currentManagerId);
                bo.setCreatorName(currentManagerName);
                bo.setCreateTime(localDateTime);
                bo.setUpdaterId(currentManagerId);
                bo.setUpdaterName(currentManagerName);
                bo.setUpdateTime(localDateTime);
                bo.setState(DataState.NORMAL.getCode());
                po = MenuBO.toPO(bo);
            } else {
                MenuPO old = menuRepository.getReferenceById(bo.getId());
                // 根据getNull复制其中的非空或包含空字段
                BeanUtil.copyProperties(bo, old, getNull);
                old.setParentId(bo.getParentId() == null ? null : bo.getParentId());
                old.setUpdaterId(currentManagerId);
                old.setUpdateTime(localDateTime);
                po = old;
            }
            return MenuBO.toBO(menuRepository.save(po));
        } catch (Exception e) {
            throw new BusinessException("新增或者修改菜单错误", e);
        }
    }

    //------------------------------------------------------------------------------------------------------------------自定义方法

    /**
     * 根据名称查询菜单列表
     *
     * @param currentPage  当前页
     * @param eachPageSize 每页多少条
     * @param pageSort     排序
     * @param menuName     菜单名称
     * @param state        状态
     * @return MenuPO 菜单对象
     */
    @Override
    public Page<MenuBO> queryByMenuNameAndState(
            int currentPage,
            int eachPageSize,
            String pageSort,
            String menuName,
            Byte state
    ) throws BusinessException {
        try {
            // 查询拼装
            Specification<MenuPO> specification = (root, query, criteriaBuilder) -> {
                Predicate predicate;
                // 条件拼装
                predicate = criteriaBuilder.and(criteriaBuilder.equal(root.get("state"), Objects.requireNonNullElse(state, DataState.NORMAL.getCode())));
                if (StringUtils.hasText(menuName)) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("menuName"), "%" + menuName + "%"));
                }
                query.where(predicate);
                // 排序拼装
                query.orderBy(PageUtil.getSort(root, criteriaBuilder, pageSort));
                // 生成拼装结果
                return query.getRestriction();
            };
            // 分页信息
            Pageable pageable = PageRequest.of(currentPage, eachPageSize);
            // 执行查询
            Page<MenuPO> doPage = menuRepository.findAll(specification, pageable);
            // 返回结果 执行查询
            return new PageImpl<>(MenuBO.toBO(doPage.getContent()), doPage.getPageable(), doPage.getTotalElements());
        } catch (Exception e) {
            throw new BusinessException("根据名称查询菜单列表错误", e);
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
            return menuRepository.updateStateByIds(DataState.DELETE.getCode(), currentManagerId, currentManagerName, LocalDateTime.now(), Stream.of(ids.split(",")).map(Long::valueOf).toList());
        } catch (Exception e) {
            throw new BusinessException("根据主键删除 主键可以是多个用,分割错误", e);
        }
    }

    /**
     * 根据主键查询
     *
     * @param id 主键ID
     * @return 菜单
     */
    @Override
    public MenuBO getById(Long id) throws BusinessException {
        try {
            return MenuBO.toBO(menuRepository.getReferenceById(id));
        } catch (Exception e) {
            throw new BusinessException("根据主键查询错误", e);
        }
    }

    /**
     * 查询菜单列表
     *
     * @return MenuBO 菜单对象
     */
    @Override
    public List<MenuBO> queryAll(
    ) throws BusinessException {
        try {
            return MenuBO.toBO(
                    menuRepository.queryAll()
            );
        } catch (Exception e) {
            throw new BusinessException("查询菜单列表错误", e);
        }
    }

    /**
     * 查询菜单列表
     *
     * @return MenuBO 菜单对象
     */
    @Override
    public List<MenuBO> queryAllParent(
    ) throws BusinessException {
        try {
            return MenuBO.toBO(
                    menuRepository.queryAllParent()
            );
        } catch (Exception e) {
            throw new BusinessException("查询父级菜单列表错误", e);
        }
    }

    /**
     * 根据管理员主键ID查询能够使用的菜单列表
     *
     * @param menuIds 菜单ID集合
     * @return 能够使用的菜单列表
     */
    @Override
    public List<MenuBO> queryHierarchyMenuByMenuIdList(
            List<Long> menuIds
    ) throws BusinessException {
        try {
            List<MenuBO> boList = MenuBO.toBO(
                    menuRepository.queryHierarchyMenuByMenuIdListAndParentIdAndState(menuIds, null, DataState.NORMAL.getCode())
            );
            //递归获取所有有权限的菜单
            this.queryStructureByManagerId(menuIds, boList);
            return boList;
        } catch (Exception e) {
            throw new BusinessException("根据管理员主键ID查询能够使用的菜单列表错误", e);
        }
    }

    /**
     * 根据管理员主键ID查询能够使用的菜单列表
     *
     * @param menuIds 菜单ID集合
     * @return 能够使用的菜单列表
     */
    @Override
    public List<MenuBO> queryMenuByMenuIdList(
            List<Long> menuIds
    ) throws BusinessException {
        try {
            return MenuBO.toBO(
                    menuRepository.queryHierarchyMenuByMenuIdListAndState(menuIds, DataState.NORMAL.getCode())
            );
        } catch (Exception e) {
            throw new BusinessException("根据管理员主键ID查询能够使用的菜单列表错误", e);
        }
    }

    //------------------------------------------------------------------------------------------------------------------私有方法

    /**
     * 递归查询所有菜单
     *
     * @param menuIds 菜单ID集合
     * @param boList  菜单集合
     */
    private void queryStructureByManagerId(List<Long> menuIds, List<MenuBO> boList) {
        for (MenuBO bo : boList) {
            bo.setSub(MenuBO.toBO(
                    menuRepository.queryHierarchyMenuByMenuIdListAndParentIdAndState(menuIds, bo.getId(), DataState.NORMAL.getCode())
            ));
            if (bo.getSub() != null) {
                this.queryStructureByManagerId(menuIds, bo.getSub());
            }
        }
    }
}
