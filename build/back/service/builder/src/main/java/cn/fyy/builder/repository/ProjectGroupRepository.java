package cn.fyy.builder.repository;

import cn.fyy.builder.bean.dbo.ProjectGroupDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 项目群 Repository
 *
 * @author fuyy
 */
@Repository
public interface ProjectGroupRepository extends JpaRepository<ProjectGroupDO, Long>, JpaSpecificationExecutor<ProjectGroupDO> {

    /**
     * 根据用户编号查询当前用户项目群数量
     *
     * @param managerId 当前登录人id
     * @param state     状态
     * @return 总数
     */
    int countByManagerIdAndState(Long managerId, Byte state);

    /**
     * 根据ids保存状态
     *
     * @param ids                主键ID(可用,分割传入多个)
     * @param state              状态
     * @param currentManagerId   当前登陆管理员id
     * @param currentManagerName 当前登陆管理员名称
     * @param updateTime         当前时间
     * @return 受影响行数
     */
    @Modifying
    @Query(value = "UPDATE ProjectGroupDO SET state = ?1, updaterId = ?2, updaterName = ?3, updateTime = ?4 WHERE id IN ?5")
    int updateStateByIds(Byte state, Long currentManagerId, String currentManagerName, LocalDateTime updateTime, List<Long> ids);

    /**
     * 根据当前登录人id查询对应的项目群列表
     *
     * @param managerId 当前登陆人主键ID
     * @return 项目群
     */
    List<ProjectGroupDO> queryByManagerIdOrderByCreateTime(Long managerId);

    /**
     * 根据主键ID和管理员主键ID查询项目群
     *
     * @param id        主键ID
     * @param managerId 管理员主键ID
     * @return 项目群
     */
    ProjectGroupDO getByIdAndManagerId(Long id, Long managerId);
}
