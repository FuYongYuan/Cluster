package cn.fyy.dictionary.repository;


import cn.fyy.dictionary.bean.dbo.ParameterDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 参数 Repository
 *
 * @author fuyy
 */
@Repository
public interface ParameterRepository extends JpaRepository<ParameterDO, Long>, JpaSpecificationExecutor<ParameterDO> {

    /**
     * 根据参数编号查询
     *
     * @param parameterCode 参数编号
     * @param state         状态
     * @return 参数
     */
    @Query(nativeQuery = true, value = "SELECT * FROM parameter WHERE parameter_code = ?1 AND state = ?2")
    ParameterDO getByParameterCodeAndState(String parameterCode, int state);

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
    @Query(value = "UPDATE ParameterDO SET state = ?1, updaterId = ?2, updaterName = ?3, updateTime = ?4 WHERE id IN ?5")
    int updateStateByIds(Byte state, Long currentManagerId, String currentManagerName, LocalDateTime updateTime, List<Long> ids);
}
