package cn.fyy.authorization.repository;

import cn.fyy.authorization.bean.po.RoleButtonPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 角色按钮关系 Repository
 *
 * @author fyy
 */
@Repository
public interface RoleButtonRepository extends JpaRepository<RoleButtonPO, Long>, JpaSpecificationExecutor<RoleButtonPO> {

    /**
     * 根据角色主键 ID 删除
     *
     * @param roleId 角色主键 ID
     */
    void deleteByRoleId(Long roleId);

}
