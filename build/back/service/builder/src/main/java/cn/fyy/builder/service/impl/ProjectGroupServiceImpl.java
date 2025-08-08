package cn.fyy.builder.service.impl;

import cn.fyy.builder.bean.bo.ProjectGroupBO;
import cn.fyy.builder.bean.po.ProjectGroupPO;
import cn.fyy.builder.feign.client.parameter.ParameterFeignClient;
import cn.fyy.builder.repository.ProjectGroupRepository;
import cn.fyy.builder.service.ProjectGroupService;
import cn.fyy.common.bean.ao.ConstantParameter;
import cn.fyy.common.bean.ao.DataState;
import cn.fyy.common.bean.ao.OperateResult;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.common.util.BeanUtil;
import cn.fyy.dictionary.bean.dto.ParameterDTO;
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
 * 项目群 Service
 *
 * @author fuyy
 */
@Slf4j
@Service
public class ProjectGroupServiceImpl implements ProjectGroupService {

    /**
     * 项目群 Repository
     */
    @Resource
    private ProjectGroupRepository projectGroupRepository;

    /**
     * 参数 FeignClient
     */
    @Resource
    private ParameterFeignClient parameterFeignClient;

    //------------------------------------------------------------------------------------------------------------------基础方法

    /**
     * 新增或者修改
     *
     * @param bo                 项目群 BO
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录人姓名
     * @return !=null 成功，==null 失败
     */
    @Override
    public ResultMessage<String> save(ProjectGroupBO bo, Long currentManagerId, String currentManagerName) throws BusinessException {
        try {
            int use = projectGroupRepository.countByManagerIdAndState(bo.getManagerId(), DataState.NORMAL.getCode()) + 1;

            ParameterDTO parameterDTO = parameterFeignClient.getByParameterCode(ConstantParameter.CREATE_PROJECT_GROUP_MAX_NUMBER).getData();
            int maxNumber = Integer.parseInt(parameterDTO.getParameterValue());

            if (bo.getId() == null && use > maxNumber) {
                return new ResultMessage<>(1, "创建失败，已满最大创建数量，请先删除无用项目群后在创建！");
            }

            ProjectGroupBO result = this.save(bo, currentManagerId, currentManagerName, false);
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
     * @param currentManagerName 当前登录人姓名
     * @param getNull            是否更新空
     * @return !=null 成功，==null 失败
     */
    @Override
    public ProjectGroupBO save(ProjectGroupBO bo, Long currentManagerId, String currentManagerName, boolean getNull) throws BusinessException {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            ProjectGroupPO po;
            if (bo.getId() == null) {
                bo.setCreatorId(currentManagerId);
                bo.setCreatorName(currentManagerName);
                bo.setCreateTime(localDateTime);
                bo.setUpdaterId(currentManagerId);
                bo.setUpdaterName(currentManagerName);
                bo.setUpdateTime(localDateTime);
                bo.setState(DataState.NORMAL.getCode());
                po = ProjectGroupBO.toPO(bo);
            } else {
                ProjectGroupPO old = projectGroupRepository.getReferenceById(bo.getId());
                // 根据getNull复制其中的非空或包含空字段
                BeanUtil.copyProperties(bo, old, getNull);
                old.setUpdaterId(currentManagerId);
                old.setUpdaterName(currentManagerName);
                old.setUpdateTime(localDateTime);
                po = old;
            }

            return ProjectGroupBO.toBO(projectGroupRepository.save(po));
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
    public ProjectGroupBO getById(Long id) throws BusinessException {
        try {
            return ProjectGroupBO.toBO(projectGroupRepository.getReferenceById(id));
        } catch (Exception e) {
            throw new BusinessException("根据主键查询错误", e);
        }
    }

    /**
     * 根据主键删除 主键可以是多个用,分割
     *
     * @param ids                删除主键 可以使用,分割
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录人姓名
     * @return 受影响行数
     * @throws BusinessException 删除错误,Exception
     */
    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public int updateDelete(String ids, Long currentManagerId, String currentManagerName) throws BusinessException {
        try {
            return projectGroupRepository.updateStateByIds(DataState.DELETE.getCode(), currentManagerId, currentManagerName, LocalDateTime.now(), Stream.of(ids.split(",")).map(Long::valueOf).toList());
        } catch (Exception e) {
            throw new BusinessException("根据主键删除 主键可以是多个用,分割错误", e);
        }
    }

    /**
     * 根据名称查询项目群列表
     *
     * @param managerId        管理员主键ID
     * @param projectGroupName 项目群名称
     * @param state            状态
     * @return TProjectGroupBO 项目群对象
     */
    @Override
    public Page<ProjectGroupBO> queryByManagerIdAndProjectGroupNameAndState(
            int currentPage,
            int eachPageSize,
            Long managerId,
            String projectGroupName,
            Byte state
    ) throws BusinessException {
        try {
            // 查询拼装
            Specification<ProjectGroupPO> specification = (root, query, criteriaBuilder) -> {
                Predicate predicate;
                // 条件拼装
                predicate = criteriaBuilder.and(criteriaBuilder.equal(root.get("state"), Objects.requireNonNullElse(state, DataState.NORMAL.getCode())));

                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("managerId"), managerId));

                if (StringUtils.hasText(projectGroupName)) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("projectGroupName"), "%" + projectGroupName + "%"));
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
            Page<ProjectGroupPO> results = projectGroupRepository.findAll(specification, pageable);
            // 返回结果
            return new PageImpl<>(ProjectGroupBO.toBO(results.getContent()), pageable, results.getTotalElements());
        } catch (Exception e) {
            throw new BusinessException("根据名称查询角色列表错误", e);
        }
    }

    /**
     * 根据当前登录人id查询对应的项目群列表
     *
     * @param managerId 当前登陆人主键ID
     */
    @Override
    public List<ProjectGroupBO> queryByManagerId(Long managerId) throws BusinessException {
        try {
            return ProjectGroupBO.toBO(projectGroupRepository.queryByManagerIdOrderByCreateTime(managerId));
        } catch (Exception e) {
            throw new BusinessException("根据主键删除 主键可以是多个用,分割错误", e);
        }
    }

//    /**
//     * 根据主键ID生成项目群压缩包
//     *
//     * @param id 主键ID
//     */
//    @Override
//    public void generateById(HttpServletRequest request, HttpServletResponse response, Long id, Long managerId) throws BusinessException {
//        String path = null;
//        try {
//            // 获取生成设置
//            ProjectGroupBO projectGroupBO = ProjectGroupBO.toBO(projectGroupRepository.getByIdAndManagerId(id, managerId));
//            if (projectGroupBO != null) {
//                List<ProjectBO> projectBOList = projectServiceImpl.queryByProjectGroupIdAndState(projectGroupBO.getId(), DataState.NORMAL.getCode());
//                // 初始化地址
//                path = ConstantParameter.projectGroupLocalPath
//                        + File.separatorChar + ConstantParameter.DOWNLOAD_PATH_NAME
//                        + File.separatorChar + projectGroupBO.getManagerId()
//                        + File.separatorChar + projectGroupBO.getId()
//                        + File.separatorChar + projectGroupBO.getArtifactId();
//                // 创建生成程序
//                GenerateSpringBoot generateSpringBoot = new GenerateSpringBoot(
//                        GenerateParameter.create()
//                                .setPath(path)
//                                .setProjectGroup(projectGroupBO)
//                                .setProjectList(projectBOList)
//                                .build()
//                );
//                // 运行
//                generateSpringBoot.run();
//
//                // 设置response的header
//                response.setContentType("application/zip;charset=UTF-8");
//                response.setHeader("Content-Disposition",
//                        "attachment;filename*=" +
//                                java.net.URLEncoder.encode(projectGroupBO.getProjectGroupName() + ".zip", "UTF-8")
//                );
//                // 直接压缩输出文件流，前端下载zip压缩包
//                ZipDispose.toZip(path, response.getOutputStream(), true);
//            }
//        } catch (Exception e) {
//            throw new BusinessException("根据主键ID生成项目群压缩包", e);
//        } finally {
//            if (path != null) {
//                File projectGroup = new File(path);
//                ConstantService.deleteFile(projectGroup);
//            }
//        }
//    }
}
