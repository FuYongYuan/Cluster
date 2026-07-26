package cn.fyy.capability.repository;

import cn.fyy.capability.bean.po.ApiPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
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
    ApiPO getByMethodMapping(String mapping);

    /**
     * 根据映射列表获取API信息
     *
     * @param mappings 映射列表
     * @return API信息列表
     */
    List<ApiPO> findByMethodMappingIn(List<String> mappings);

    /**
     * 查询所有可用API信息
     *
     * @return 能够使用的API信息列表
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM api " +
                    "WHERE state = 0"
    )
    List<ApiPO> queryAll();

    /**
     * 根据Api信息 ID 集合查询Api信息列表
     *
     * @param apiIdList Api信息 ID 集合
     * @param state     状态
     * @return 能够使用的Api信息列表
     */
    @Query(nativeQuery = true,
            value = "SELECT * FROM api " +
                    "WHERE id IN ?1 " +
                    "AND state = ?2 "
    )
    List<ApiPO> queryApiByApiIdListAndState(List<Long> apiIdList, int state);

}
