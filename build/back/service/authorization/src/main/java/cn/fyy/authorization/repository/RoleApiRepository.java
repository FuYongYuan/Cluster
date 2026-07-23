package cn.fyy.authorization.repository;

import cn.fyy.authorization.bean.po.RoleApiPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

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

}
