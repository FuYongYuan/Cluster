package cn.fyy.jwt.config.security.bean.bo;


import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.math.BigInteger;
import java.util.Collection;

/**
 * Security Token 实体 - 认证使用
 *
 * @author fuyy
 */
@Getter
public class SecurityUser extends User {

    /**
     * 管理员ID
     */
    private BigInteger managerId;

    /**
     * 管理员名称
     */
    private String managerName;

    /**
     * 初始化
     *
     * @param managerId   管理员ID
     * @param managerName 管理员名称
     * @param username    用户账号
     * @param password    用户密码
     * @param authorities 权限ID集合
     */
    public SecurityUser(BigInteger managerId, String managerName, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.managerId = managerId;
        this.managerName = managerName;
    }

    /**
     * 管理员ID
     */
    public SecurityUser setManagerId(BigInteger managerId) {
        this.managerId = managerId;
        return this;
    }

    /**
     * 管理员名称
     */
    public SecurityUser setManagerName(String managerName) {
        this.managerName = managerName;
        return this;
    }
}
