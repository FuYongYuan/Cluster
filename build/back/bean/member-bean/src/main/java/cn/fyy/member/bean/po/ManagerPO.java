package cn.fyy.member.bean.po;

import cn.fyy.jpa.bean.po.BasePO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
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
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ManagerPO extends BasePO implements Serializable {

    /**
     * 头像
     */
    @Column(name = "avatar", length = 1000)
    private String avatar;

    /**
     * 姓名
     */
    @Column(name = "manager_name", length = 50)
    private String managerName;

    /**
     * 年龄
     */
    @Column(name = "age")
    private Byte age;

    /**
     * 性别;（0.保密、1.男、2女）
     */
    @Column(name = "sex")
    private Byte sex;

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
     * 最后尝试请求 IP
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
     * 常用请求 IP
     */
    @Column(name = "request_ip", length = 100)
    private String requestIp;

    /**
     * 当前请求 IP
     */
    @Column(name = "current_request_ip", length = 100)
    private String currentRequestIp;

    /**
     * 备注
     */
    @Column(name = "remark", length = 1000)
    private String remark;

    //------------------------------------------------------------------------------------------------------------------基础方法
    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ManagerPO that)) {
            return false;
        }
        if (!super.equals(that)) {
            return false;
        }

        return Objects.equals(avatar, that.avatar) &&
                Objects.equals(managerName, that.managerName) &&
                Objects.equals(age, that.age) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(mobile, that.mobile) &&
                Objects.equals(mail, that.mail) &&
                Objects.equals(account, that.account) &&
                Objects.equals(loginPassword, that.loginPassword) &&
                Objects.equals(lastAttemptLoginRequestIp, that.lastAttemptLoginRequestIp) &&
                Objects.equals(lastAttemptLoginTime, that.lastAttemptLoginTime) &&
                Objects.equals(attemptLoginNumber, that.attemptLoginNumber) &&
                Objects.equals(requestIp, that.requestIp) &&
                Objects.equals(currentRequestIp, that.currentRequestIp) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(avatar);
        result = 31 * result + Objects.hashCode(managerName);
        result = 31 * result + Objects.hashCode(age);
        result = 31 * result + Objects.hashCode(sex);
        result = 31 * result + Objects.hashCode(mobile);
        result = 31 * result + Objects.hashCode(mail);
        result = 31 * result + Objects.hashCode(account);
        result = 31 * result + Objects.hashCode(loginPassword);
        result = 31 * result + Objects.hashCode(lastAttemptLoginRequestIp);
        result = 31 * result + Objects.hashCode(lastAttemptLoginTime);
        result = 31 * result + Objects.hashCode(attemptLoginNumber);
        result = 31 * result + Objects.hashCode(requestIp);
        result = 31 * result + Objects.hashCode(currentRequestIp);
        result = 31 * result + Objects.hashCode(remark);
        return result;
    }
}
