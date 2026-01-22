package cn.fyy.builder.repository;

import cn.fyy.builder.bean.po.ProjectGroupPO;
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
public interface ProjectGroupRepository extends JpaRepository<ProjectGroupPO, Long>, JpaSpecificationExecutor<ProjectGroupPO> {

    /**
     * 根据用户编号查询当前用户项目群数量
     *
     * @param managerId 当前登录人 ID
     * @param state     状态
     * @return 总数
     */
    int countByManagerIdAndState(Long managerId, Byte state);

    /**
     * 根据 ids 保存状态
     *
     * @param ids                主键 ID (可用,分割传入多个)
     * @param state              状态
     * @param currentManagerId   当前登陆管理员 ID
     * @param currentManagerName 当前登陆管理员名称
     * @param updateTime         当前时间
     * @return 受影响行数
     */
    @Modifying
    @Query(value = "UPDATE ProjectGroupPO SET state = ?1, updaterId = ?2, updaterName = ?3, updateTime = ?4 WHERE id IN ?5")
    int updateStateByIds(Byte state, Long currentManagerId, String currentManagerName, LocalDateTime updateTime, List<Long> ids);

    /**
     * 根据当前登录人 ID 查询对应的项目群列表
     *
     * @param managerId 当前登陆人主键 ID
     * @return 项目群
     */
    List<ProjectGroupPO> queryByManagerIdOrderByCreateTime(Long managerId);

    /**
     * 根据主键 ID 和管理员主键 ID 查询项目群
     *
     * @param id        主键 ID
     * @param managerId 管理员主键 ID
     * @return 项目群
     */
    ProjectGroupPO getByIdAndManagerId(Long id, Long managerId);
}
