package cn.fyy.jwt.encrypt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 密码加密器
 *
 * @author fuyy
 */
@Configuration
public class PasswordEncoderConfig {

    /**
     * SpringSecurity定义的用于对密码进行编码及比对的接口，
     * 目前使用的是BCryptPasswordEncoder；
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}