package cn.fyy.jwt.encrypt;

import cn.fyy.jwt.config.jwt.JwtProperties;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.stereotype.Component;

/**
 * JWT编码器、解码器工具类
 *
 * @author fuyy
 */
@Slf4j
@Component
public class JwtEncrypt {
    /**
     * JWT参数
     */
    @Resource
    private JwtProperties jwtProperties;

    /**
     * JWT解码器
     */
    @Bean
    public JwtDecoder decoder() {
        return NimbusJwtDecoder.withPublicKey(jwtProperties.getPublicKey()).build();
    }

    /**
     * JWT编码器
     */
    @Bean
    public JwtEncoder encoder() {
        JWK jwk = new RSAKey.Builder(jwtProperties.getPublicKey()).privateKey(jwtProperties.getPrivateKey()).build();
        JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwkSource);
    }
}
