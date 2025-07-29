package cn.fyy.builder.service.impl;

import cn.fyy.builder.bean.bo.ProjectBO;
import cn.fyy.builder.bean.dbo.ProjectDO;
import cn.fyy.builder.repository.ProjectRepository;
import cn.fyy.builder.service.ProjectService;
import cn.fyy.common.bean.ao.DataState;
import cn.fyy.common.bean.ao.OperateResult;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import dispose.CopyClass;
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
 * 项目 Service
 *
 * @author fuyy
 */
@Slf4j
@Service
public class ProjectServiceImpl implements ProjectService {

    /**
     * 项目 Repository
     */
    @Resource
    private ProjectRepository projectRepository;

    //------------------------------------------------------------------------------------------------------------------基础方法

    /**
     * 新增或者修改
     *
     * @param bo                 项目群 BO
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录人名称
     * @return !=null 成功，==null 失败
     */
    @Override
    public ResultMessage<String> save(ProjectBO bo, Long currentManagerId, String currentManagerName) throws BusinessException {
        try {
            ProjectBO result = this.save(bo, currentManagerId, currentManagerName, false);
            if (result != null) {
                return new ResultMessage<>(OperateResult.SUCCESS.getMessage());
            } else {
                return new ResultMessage<>(1, OperateResult.FAIL.getMessage());
            }
        } catch (Exception e) {
            throw new BusinessException("新增或者修改项目群错误", e);
        }
    }

    /**
     * 新增或者修改
     *
     * @param bo                 项目群 BO
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录人名称
     * @param getNull            是否更新空
     * @return !=null 成功，==null 失败
     */
    @Override
    public ProjectBO save(ProjectBO bo, Long currentManagerId, String currentManagerName, boolean getNull) throws BusinessException {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            ProjectDO dbo;
            if (bo.getId() == null) {
                bo.setCreatorId(currentManagerId);
                bo.setCreatorName(currentManagerName);
                bo.setCreateTime(localDateTime);
                bo.setUpdaterId(currentManagerId);
                bo.setUpdaterName(currentManagerName);
                bo.setUpdateTime(localDateTime);
                bo.setState(DataState.NORMAL.getCode());
                dbo = ProjectBO.toDO(bo);
            } else {
                ProjectDO old = projectRepository.getReferenceById(bo.getId());
                // 根据getNull复制其中的非空或包含空字段
                CopyClass.copyClassGetSet(bo, old, ProjectDO.class, getNull);
                old.setUpdaterId(currentManagerId);
                old.setUpdaterName(currentManagerName);
                old.setUpdateTime(localDateTime);
                dbo = old;
            }

            return ProjectBO.toBO(projectRepository.save(dbo));
        } catch (Exception e) {
            throw new BusinessException("新增或者修改项目群错误", e);
        }
    }

    //------------------------------------------------------------------------------------------------------------------自定义方法

    /**
     * 根据主键查询
     *
     * @param id 主键ID
     * @return 项目群
     */
    @Override
    public ProjectBO getById(Long id) throws BusinessException {
        try {
            return ProjectBO.toBO(projectRepository.getReferenceById(id));
        } catch (Exception e) {
            throw new BusinessException("根据主键查询错误", e);
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
            return projectRepository.updateStateByIds(DataState.DELETE.getCode(), currentManagerId, currentManagerName, LocalDateTime.now(), Stream.of(ids.split(",")).map(Long::valueOf).toList());
        } catch (Exception e) {
            throw new BusinessException("根据主键删除 主键可以是多个用,分割错误", e);
        }
    }

    /**
     * 根据名称查询项目列表
     *
     * @param projectGroupId 项目群主键ID
     * @param projectName    项目名称
     * @param state          状态
     * @return T_Project 项目对象
     */
    @Override
    public Page<ProjectBO> queryByProjectGroupIdAndProjectNameAndState(int currentPage, int eachPageSize, Long projectGroupId, String projectName, Integer state) throws BusinessException {
        try {
            // 查询拼装
            Specification<ProjectDO> specification = (root, query, criteriaBuilder) -> {
                Predicate predicate;
                // 条件拼装
                predicate = criteriaBuilder.and(criteriaBuilder.equal(root.get("state"), Objects.requireNonNullElse(state, DataState.NORMAL.getCode())));

                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("projectGroupID"), projectGroupId));

                if (StringUtils.hasText(projectName)) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("projectName"), "%" + projectName + "%"));
                }
                query.where(predicate);
                // 排序拼装
                query.orderBy(criteriaBuilder.asc(root.get("createTime")));
                // 生成拼装结果
                return query.getRestriction();
            };
            // 分页信息
            Pageable pageable = PageRequest.of(currentPage, eachPageSize);
            // 执行查询
            Page<ProjectDO> results = projectRepository.findAll(specification, pageable);
            // 返回结果
            return new PageImpl<>(ProjectBO.toBO(results.getContent()), pageable, results.getTotalElements());
        } catch (Exception e) {
            throw new BusinessException("根据名称查询角色列表错误", e);
        }
    }

    /**
     * 根据名称查询项目列表
     *
     * @param projectGroupId 项目群主键ID
     * @param state          状态
     * @return T_Project 项目对象
     */
    @Override
    public List<ProjectBO> queryByProjectGroupIdAndState(Long projectGroupId, Byte state) throws BusinessException {
        try {
            return ProjectBO.toBO(projectRepository.queryByProjectGroupIdAndState(projectGroupId, state));
        } catch (Exception e) {
            throw new BusinessException("根据名称查询项目列表", e);
        }
    }


}
