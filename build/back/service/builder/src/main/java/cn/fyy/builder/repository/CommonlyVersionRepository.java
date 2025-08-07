package cn.fyy.builder.repository;

import cn.fyy.builder.bean.po.CommonlyVersionPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 常用版本 Repository
 *
 * @author fuyy
 */
@Repository
public interface CommonlyVersionRepository extends JpaRepository<CommonlyVersionPO, Long>, JpaSpecificationExecutor<CommonlyVersionPO> {

    /**
     * 根据用户主键查询对应的数据
     *
     * @param managerId 管理员主键ID
     * @return 常用版本
     */
    CommonlyVersionPO getByManagerId(Long managerId);

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
    @Query(value = "UPDATE CommonlyVersionPO SET state = ?1, updaterId = ?2, updaterName = ?3, updateTime = ?4 WHERE id IN ?5")
    int updateStateByIds(Byte state, Long currentManagerId, String currentManagerName, LocalDateTime updateTime, List<Long> ids);


}
