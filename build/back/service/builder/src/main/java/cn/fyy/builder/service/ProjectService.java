package cn.fyy.builder.service;

import cn.fyy.builder.bean.bo.ProjectBO;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import org.springframework.data.domain.Page;

import java.math.BigInteger;
import java.util.List;

/**
 * 项目 Service
 *
 * @author fuyy
 */
public interface ProjectService {
    /**
     * 新增或者修改
     *
     * @param bo                 项目 BO
     * @param currentManagerId   当前登录人id
     * @param currentManagerName 当前登录人名称
     * @return !=null 成功，==null 失败
     * @throws BusinessException 错误
     */
    ResultMessage<String> save(ProjectBO bo, BigInteger currentManagerId, String currentManagerName) throws BusinessException;

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
    ProjectBO save(ProjectBO bo, BigInteger currentManagerId, String currentManagerName, boolean getNull) throws BusinessException;

    /**
     * 根据主键查询
     *
     * @param id 主键ID
     * @return 项目
     * @throws BusinessException 错误
     */
    ProjectBO getById(BigInteger id) throws BusinessException;

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
     * 根据名称查询项目列表
     *
     * @param currentPage    当前页
     * @param eachPageSize   每页多少条
     * @param projectGroupId 项目群主键ID
     * @param projectName    项目名称
     * @param state          状态
     * @return T_Project 项目对象
     * @throws BusinessException 错误
     */
    Page<ProjectBO> queryByProjectGroupIdAndProjectNameAndState(
            int currentPage,
            int eachPageSize,
            BigInteger projectGroupId,
            String projectName,
            Integer state
    ) throws BusinessException;


    /**
     * 根据名称查询项目列表
     *
     * @param projectGroupId 项目群主键ID
     * @param state          状态
     * @return T_Project 项目对象
     * @throws BusinessException 错误
     */
    List<ProjectBO> queryByProjectGroupIdAndState(
            BigInteger projectGroupId,
            Integer state
    ) throws BusinessException;
}
