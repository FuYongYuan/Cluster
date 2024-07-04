package cn.fyy.jwt.config.security.bean.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * Security Token 实体 - 存储Redis用
 *
 * @author fuyy
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class SecurityRedis {
    /**
     * 所属 管理员Id 即 Redis 中key
     */
    private BigInteger managerId;

    /**
     * 所属 管理员名称
     */
    private String managerName;

    /**
     * 所属 token
     */
    private String token;

    /**
     * security 用户名称
     */
    private String username;

    /**
     * security 密码
     */
    private String password;

    /**
     * security 中权限
     */
    private String[] authorities;

    /**
     * 剩余过期时间
     */
    private Long seconds;
}
