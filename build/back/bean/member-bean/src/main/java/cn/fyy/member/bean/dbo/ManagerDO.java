package cn.fyy.member.bean.dbo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 管理员
 *
 * @author fuyy
 */
@Entity
@Table(name = "manager", schema = "member")
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ManagerDO implements Serializable {

    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    /**
     * 头像
     */
    @Column(name = "head_img_url", length = 1000)
    private String headImgUrl;

    /**
     * 姓名
     */
    @Column(name = "manager_name", length = 50)
    private String managerName;

    /**
     * 年龄
     */
    @Column(name = "age")
    private Integer age;

    /**
     * 性别(0.保密 1.男 2女)
     */
    @Column(name = "sex")
    private Integer sex;

    /**
     * 手机号码
     */
    @Column(name = "mobile", length = 20)
    private String mobile;

    /**
     * 邮箱
     */
    @Column(name = "mail", length = 200)
    private String mail;

    /**
     * 账号
     */
    @Column(name = "account", length = 50)
    private String account;

    /**
     * 登陆密码
     */
    @Column(name = "login_password", length = 32)
    private String loginPassword;

    /**
     * 最后尝试请求IP
     */
    @Column(name = "last_attempt_login_request_ip", length = 100)
    private String lastAttemptLoginRequestIp;

    /**
     * 最后尝试登陆时间
     */
    @Column(name = "last_attempt_login_time")
    private LocalDateTime lastAttemptLoginTime;

    /**
     * 尝试登陆次数
     */
    @Column(name = "attempt_login_number")
    private Integer attemptLoginNumber;

    /**
     * 常用请求IP
     */
    @Column(name = "request_ip", length = 100)
    private String requestIp;

    /**
     * 当前请求IP
     */
    @Column(name = "current_request_ip", length = 100)
    private String currentRequestIp;

    /**
     * 备注
     */
    @Column(name = "remark", length = 1000)
    private String remark;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 创建人ID
     */
    @Column(name = "creator_id")
    private BigInteger creatorId;

    /**
     * 创建人名称
     */
    @Column(name = "creator_name", length = 50)
    private String creatorName;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    /**
     * 修改人ID
     */
    @Column(name = "updater_id")
    private BigInteger updaterId;

    /**
     * 修改人名称
     */
    @Column(name = "updater_name", length = 50)
    private String updaterName;

    /**
     * 状态(0.正常 1.冻结 99.删除)
     */
    @Column(name = "state")
    private Integer state;

    //------------------------------------------------------------------------------------------------------------------基础方法
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ManagerDO managerDO)) {
            return false;
        }

        if (!id.equals(managerDO.id)) {
            return false;
        }
        if (!Objects.equals(headImgUrl, managerDO.headImgUrl)) {
            return false;
        }
        if (!Objects.equals(managerName, managerDO.managerName)) {
            return false;
        }
        if (!Objects.equals(age, managerDO.age)) {
            return false;
        }
        if (!Objects.equals(sex, managerDO.sex)) {
            return false;
        }
        if (!Objects.equals(mobile, managerDO.mobile)) {
            return false;
        }
        if (!Objects.equals(mail, managerDO.mail)) {
            return false;
        }
        if (!Objects.equals(account, managerDO.account)) {
            return false;
        }
        if (!Objects.equals(loginPassword, managerDO.loginPassword)) {
            return false;
        }
        if (!Objects.equals(lastAttemptLoginRequestIp, managerDO.lastAttemptLoginRequestIp)) {
            return false;
        }
        if (!Objects.equals(lastAttemptLoginTime, managerDO.lastAttemptLoginTime)) {
            return false;
        }
        if (!Objects.equals(attemptLoginNumber, managerDO.attemptLoginNumber)) {
            return false;
        }
        if (!Objects.equals(requestIp, managerDO.requestIp)) {
            return false;
        }
        if (!Objects.equals(currentRequestIp, managerDO.currentRequestIp)) {
            return false;
        }
        if (!Objects.equals(remark, managerDO.remark)) {
            return false;
        }
        if (!Objects.equals(createTime, managerDO.createTime)) {
            return false;
        }
        if (!Objects.equals(creatorId, managerDO.creatorId)) {
            return false;
        }
        if (!Objects.equals(creatorName, managerDO.creatorName)) {
            return false;
        }
        if (!Objects.equals(updateTime, managerDO.updateTime)) {
            return false;
        }
        if (!Objects.equals(updaterId, managerDO.updaterId)) {
            return false;
        }
        if (!Objects.equals(updaterName, managerDO.updaterName)) {
            return false;
        }
        return Objects.equals(state, managerDO.state);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (headImgUrl != null ? headImgUrl.hashCode() : 0);
        result = 31 * result + (managerName != null ? managerName.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (loginPassword != null ? loginPassword.hashCode() : 0);
        result = 31 * result + (lastAttemptLoginRequestIp != null ? lastAttemptLoginRequestIp.hashCode() : 0);
        result = 31 * result + (lastAttemptLoginTime != null ? lastAttemptLoginTime.hashCode() : 0);
        result = 31 * result + (attemptLoginNumber != null ? attemptLoginNumber.hashCode() : 0);
        result = 31 * result + (requestIp != null ? requestIp.hashCode() : 0);
        result = 31 * result + (currentRequestIp != null ? currentRequestIp.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (creatorId != null ? creatorId.hashCode() : 0);
        result = 31 * result + (creatorName != null ? creatorName.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (updaterId != null ? updaterId.hashCode() : 0);
        result = 31 * result + (updaterName != null ? updaterName.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}
