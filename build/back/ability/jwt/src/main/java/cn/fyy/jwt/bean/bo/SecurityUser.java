package cn.fyy.jwt.bean.bo;


import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Security Token 实体 - 认证使用
 *
 * @author fyy
 */
@Getter
public class SecurityUser extends User {

    /**
     * 管理员 ID
     */
    private Long managerId;

    /**
     * 管理员名称
     */
    private String managerName;

    /**
     * 初始化
     *
     * @param managerId   管理员 ID
     * @param managerName 管理员名称
     * @param username    用户账号
     * @param password    用户密码
     * @param authorities 权限 ID 集合
     */
    public SecurityUser(Long managerId, String managerName, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.managerId = managerId;
        this.managerName = managerName;
    }

    /**
     * 管理员 ID
     */
    public SecurityUser setManagerId(Long managerId) {
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
