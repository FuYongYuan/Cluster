package cn.fyy.builder.repository;

import cn.fyy.builder.bean.dbo.ProjectDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * 项目 Repository
 *
 * @author fuyy
 */
@Repository
public interface ProjectRepository extends JpaRepository<ProjectDO, BigInteger>, JpaSpecificationExecutor<ProjectDO> {
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
    @Query(value = "UPDATE ProjectDO SET state = ?1, updaterId = ?2, updaterName = ?3, updateTime = ?4 WHERE id IN ?5")
    int updateStateByIds(Integer state, BigInteger currentManagerId, String currentManagerName, Date updateTime, List<BigInteger> ids);


    /**
     * 根据名称查询项目列表
     *
     * @param projectGroupId 项目群主键ID
     * @param state          状态
     * @return T_Project 项目对象
     */
    List<ProjectDO> queryByProjectGroupIdAndState(BigInteger projectGroupId, Integer state);
}