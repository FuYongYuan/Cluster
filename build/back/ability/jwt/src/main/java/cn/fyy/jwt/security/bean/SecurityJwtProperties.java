package cn.fyy.jwt.security.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;

/**
 * Security 配置
 *
 * @author fuyy
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class SecurityJwtProperties {
    /**
     * 私钥
     */
    private RSAPrivateKey privateKey;

    /**
     * 公钥
     */
    private RSAPublicKey publicKey;

    /**
     * 过期时间
     */
    private Duration accessTokenExpireTime;

    /**
     * 刷新过期时间
     */
    private Duration refreshTokenExpireTime;

    /**
     * APP刷新过期时间
     */
    private Duration refreshTokenExpireAppTime;

    /**
     * 签发系统
     */
    private String issuer;

    /**
     * JWT存储的请求头
     */
    private String tokenHeader;

    /**
     * JWT负载中拿到开头
     */
    private String tokenHead;

    /**
     * 是否输出相关日志
     */
    private Boolean showLog;

}
