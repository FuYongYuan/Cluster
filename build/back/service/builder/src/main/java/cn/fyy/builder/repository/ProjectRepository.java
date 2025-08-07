package cn.fyy.builder.repository;

import cn.fyy.builder.bean.po.ProjectPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 项目 Repository
 *
 * @author fuyy
 */
@Repository
public interface ProjectRepository extends JpaRepository<ProjectPO, Long>, JpaSpecificationExecutor<ProjectPO> {
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
    @Query(value = "UPDATE ProjectPO SET state = ?1, updaterId = ?2, updaterName = ?3, updateTime = ?4 WHERE id IN ?5")
    int updateStateByIds(Byte state, Long currentManagerId, String currentManagerName, LocalDateTime updateTime, List<Long> ids);


    /**
     * 根据名称查询项目列表
     *
     * @param projectGroupId 项目群主键ID
     * @param state          状态
     * @return T_Project 项目对象
     */
    List<ProjectPO> queryByProjectGroupIdAndState(Long projectGroupId, Byte state);
}
