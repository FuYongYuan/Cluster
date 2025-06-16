package cn.fyy.authorization.repository;

import cn.fyy.authorization.bean.dbo.RoleDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 角色 Repository
 *
 * @author fuyy
 */
@Repository
public interface RoleRepository extends JpaRepository<RoleDO, BigInteger>, JpaSpecificationExecutor<RoleDO> {

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
    @Query(value = "UPDATE RoleDO SET state = ?1, updaterId = ?2, updaterName = ?3, updateTime = ?4 WHERE id IN ?5")
    int updateStateByIds(Integer state, BigInteger currentManagerId, String currentManagerName, LocalDateTime updateTime, List<BigInteger> ids);

    /**
     * 根据管理员主键ID查询能够使用的角色列表
     *
     * @param managerId 管理员主键ID
     * @param state     状态
     * @return 能够使用的角色列表
     */
    @Query(nativeQuery = true,
            value = "SELECT r.* FROM role r " +
                    "LEFT JOIN role_manager rm ON r.id = rm.role_id " +
                    "WHERE rm.manager_id = ?1 " +
                    "AND r.state = ?2 " +
                    "ORDER BY r.update_time"
    )
    List<RoleDO> queryManagerHaveRoleByManagerIdAndState(BigInteger managerId, int state);

    /**
     * 查询所有可用角色
     *
     * @return 能够使用的角色列表
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM role " +
                    "WHERE state = 0"
    )
    List<RoleDO> queryAll();

}
