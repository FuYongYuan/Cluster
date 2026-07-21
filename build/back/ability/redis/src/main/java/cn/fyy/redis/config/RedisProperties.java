package cn.fyy.redis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * redis配置类
 *
 * @author fyy
 */
@Data
@ConfigurationProperties(prefix = "spring.data.redis")
public class RedisProperties {
    private String host = "localhost";
    private int port = 6379;
    private String password;
    private int database = 0;
}