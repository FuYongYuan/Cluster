package cn.fyy.capability.repository;

import cn.fyy.capability.bean.po.ApiPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * API信息 Repository
 *
 * @author fyy
 */
@Repository
public interface ApiRepository extends JpaRepository<ApiPO, Long>, JpaSpecificationExecutor<ApiPO> {

}
