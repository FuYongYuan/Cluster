package cn.fyy.authorization.repository;


import cn.fyy.authorization.bean.dbo.RoleMenuDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * 角色菜单关系 Repository
 *
 * @author fuyy
 */
@Repository
public interface RoleMenuRepository extends JpaRepository<RoleMenuDO, BigInteger>, JpaSpecificationExecutor<RoleMenuDO> {

    /**
     * 根据角色主键ID删除
     *
     * @param roleId 角色主键ID
     */
    void deleteByRoleId(BigInteger roleId);

    /**
     * 根据角色查询现有菜单关系列表
     *
     * @param roleId 角色主键ID
     * @return 现有菜单关系列表
     */
    List<RoleMenuDO> queryByRoleIdOrderByUpdateTimeAsc(BigInteger roleId);

    /**
     * 根据角色ID集合查询现有菜单关系列表
     *
     * @param roleIds 角色主键ID集合
     * @param state   状态
     * @return 现有菜单关系列表
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM role_menu " +
                    "WHERE role_id IN ?1 " +
                    "AND state = ?2 "
    )
    List<RoleMenuDO> queryByRoleIdsAndState(List<BigInteger> roleIds, int state);
}
