package cn.fyy.authorization.repository;

import cn.fyy.authorization.bean.po.RoleApiPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色API接口访问关系 Repository
 *
 * @author fyy
 */
@Repository
public interface RoleApiRepository extends JpaRepository<RoleApiPO, Long>, JpaSpecificationExecutor<RoleApiPO> {

    /**
     * 根据角色主键 ID 删除
     *
     * @param roleId 角色主键 ID
     */
    void deleteByRoleId(Long roleId);

    /**
     * 根据角色主键 ID 查询关系列表
     *
     * @param roleId 角色主键 ID
     * @return 关系列表
     */
    List<RoleApiPO> findByRoleId(Long roleId);

    /**
     * 根据角色 ID 集合查询现有API信息关系列表
     *
     * @param roleIds 角色主键 ID 集合
     * @param state   状态
     * @return 现有API信息关系列表
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM role_api " +
                    "WHERE role_id IN ?1 " +
                    "AND state = ?2 "
    )
    List<RoleApiPO> queryByRoleIdsAndState(List<Long> roleIds, int state);
}
