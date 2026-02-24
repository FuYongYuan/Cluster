package cn.fyy.capability.repository;

import cn.fyy.capability.bean.po.ApiPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * API信息 Repository
 *
 * @author fyy
 */
@Repository
public interface ApiRepository extends JpaRepository<ApiPO, Long>, JpaSpecificationExecutor<ApiPO> {

    /**
     * 根据映射获取API信息
     *
     * @param mapping 映射
     * @return API信息
     */
    ApiPO getByMapping(String mapping);

    /**
     * 根据映射列表获取API信息
     *
     * @param mappings 映射列表
     * @return API信息列表
     */
    List<ApiPO> findByMappingIn(List<String> mappings);
}
