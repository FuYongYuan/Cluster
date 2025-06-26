package cn.fyy.builder.service;

import cn.fyy.builder.bean.bo.ProjectGroupBO;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 项目群 Service
 *
 * @author fuyy
 */
public interface ProjectGroupService {
    /**
     * 新增或者修改
     *
     * @param bo                 项目群 BO
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录用户名称
     * @return !=null 成功，==null 失败
     * @throws BusinessException 错误
     */
    ResultMessage<String> save(ProjectGroupBO bo, Long currentManagerId, String currentManagerName) throws BusinessException;

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
    ProjectGroupBO save(ProjectGroupBO bo, Long currentManagerId, String currentManagerName, boolean getNull) throws BusinessException;

    /**
     * 根据主键查询
     *
     * @param id 主键ID
     * @return 项目群
     * @throws BusinessException 错误
     */
    ProjectGroupBO getById(Long id) throws BusinessException;

    /**
     * 根据主键删除 主键可以是多个用,分割
     *
     * @param ids                删除主键 可以使用,分割
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录人名称
     * @return 受影响行数
     * @throws BusinessException 删除错误,Exception
     */
    int updateDelete(String ids, Long currentManagerId, String currentManagerName) throws BusinessException;


    /**
     * 根据名称查询项目群列表
     *
     * @param currentPage      当前页
     * @param eachPageSize     每页多少条
     * @param managerId        管理员主键ID
     * @param projectGroupName 项目群名称
     * @param state            状态
     * @return T_ProjectGroup 项目群对象
     * @throws BusinessException 错误
     */
    Page<ProjectGroupBO> queryByManagerIdAndProjectGroupNameAndState(
            int currentPage,
            int eachPageSize,
            Long managerId,
            String projectGroupName,
            Integer state
    ) throws BusinessException;

    /**
     * 根据当前登录人id查询对应的项目群列表
     *
     * @param managerId 当前登陆人主键ID
     * @return 项目群
     * @throws BusinessException 错误
     */
    List<ProjectGroupBO> queryByManagerId(Long managerId) throws BusinessException;
//
//    /**
//     * 根据主键ID生成项目群压缩包
//     *
//     * @param id 主键ID
//     * @param managerId 管理员ID
//     * @param request  request
//     * @param response response
//     * @throws BusinessException 错误
//     */
//    void generateById(HttpServletRequest request, HttpServletResponse response, Long id, Long managerId) throws BusinessException;
}
