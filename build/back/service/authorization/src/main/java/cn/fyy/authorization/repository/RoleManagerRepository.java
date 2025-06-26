package cn.fyy.authorization.repository;


import cn.fyy.authorization.bean.dbo.RoleManagerDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 用户角色 Repository
 *
 * @author fuyy
 */
@Repository
public interface RoleManagerRepository extends JpaRepository<RoleManagerDO, Long>, JpaSpecificationExecutor<RoleManagerDO> {

    /**
     * 根据管理员主键ID删除
     *
     * @param managerId 管理员主键ID
     */
    void deleteByManagerId(Long managerId);

}
