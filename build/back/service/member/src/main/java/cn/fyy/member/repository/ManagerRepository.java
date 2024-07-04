package cn.fyy.member.repository;


import cn.fyy.member.bean.dbo.ManagerDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * 管理员 Repository
 *
 * @author fuyy
 */
@Repository
public interface ManagerRepository extends JpaRepository<ManagerDO, BigInteger>, JpaSpecificationExecutor<ManagerDO> {

    /**
     * 根据账号查询对应的管理员
     *
     * @param account 账号
     * @return 管理员
     */
    ManagerDO getByAccountIgnoreCase(String account);

    /**
     * 根据电子邮箱查询对应的管理员
     *
     * @param mail 电子邮箱
     * @return 管理员
     */
    ManagerDO getByMailIgnoreCase(String mail);

    /**
     * 根据账号密码查询对应的管理员
     *
     * @param account       账号
     * @param loginPassword 密码
     * @return 管理员
     */
    ManagerDO getByAccountAndLoginPassword(String account, String loginPassword);

    /**
     * 查询尝试次数大于0的管理员
     *
     * @param attemptLoginNumber 尝试次数
     * @return 尝试次数大于集合
     */
    List<ManagerDO> queryByAttemptLoginNumberGreaterThan(Integer attemptLoginNumber);

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
    @Query(value = "UPDATE ManagerDO SET state = ?1, updaterId = ?2, updaterName = ?3, updateTime = ?4 WHERE id IN ?5")
    int updateStateByIds(Integer state, BigInteger currentManagerId, String currentManagerName, Date updateTime, List<BigInteger> ids);

    /**
     * 根据邮箱保存密码
     *
     * @param mail          邮箱
     * @param loginPassword 密码
     * @return 受影响行数
     */
    @Modifying
    @Query(value = "UPDATE ManagerDO SET loginPassword = ?2 WHERE mail = ?1")
    int updateLoginPasswordByMail(String mail, String loginPassword);

}
