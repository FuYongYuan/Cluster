package cn.fyy.authorization.repository;


import cn.fyy.authorization.bean.po.RoleManagerPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 用户角色 Repository
 *
 * @author fuyy
 */
@Repository
public interface RoleManagerRepository extends JpaRepository<RoleManagerPO, Long>, JpaSpecificationExecutor<RoleManagerPO> {

    /**
     * 根据管理员主键ID删除
     *
     * @param managerId 管理员主键ID
     */
    void deleteByManagerId(Long managerId);

}
