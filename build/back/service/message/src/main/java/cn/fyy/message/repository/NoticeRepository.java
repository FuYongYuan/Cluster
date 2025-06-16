package cn.fyy.message.repository;

import cn.fyy.message.bean.dbo.NoticeDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 公告 Repository
 *
 * @author fuyy
 */
@Repository
public interface NoticeRepository extends JpaRepository<NoticeDO, BigInteger>, JpaSpecificationExecutor<NoticeDO> {

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
    @Query(value = "UPDATE NoticeDO SET state = ?1, updaterId = ?2, updaterName = ?3, updateTime = ?4 WHERE id IN ?5")
    int updateStateByIds(Integer state, BigInteger currentManagerId, String currentManagerName, LocalDateTime updateTime, List<BigInteger> ids);

}
