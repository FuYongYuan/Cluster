package cn.fyy.gateway.config.security.converter;

import cn.fyy.jwt.security.util.JwtTokenUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * JWT 认证转换器
 *
 * @author fyy
 */
@Slf4j
@Component
public class JwtAuthConverter implements ServerAuthenticationConverter {

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 认证
     *
     * @param exchange 请求
     * @return 认证
     */
    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        return Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst("Authorization"))
                .filter(token -> token.startsWith("Bearer "))
                .map(token -> token.substring(7))
                .flatMap(this::createAuthentication);
    }

    /**
     * 创建认证
     *
     * @param token 令牌
     * @return 认证
     */
    private Mono<Authentication> createAuthentication(String token) {
        try {
            String username = jwtTokenUtil.getUserNameFromToken(token);
            List<String> authorities = jwtTokenUtil.getAuthoritiesFromToken(token);
            log.debug("用户名:{}, 权限:{}", username, authorities);
            return Mono.just(new UsernamePasswordAuthenticationToken(username, null, AuthorityUtils.createAuthorityList(authorities.toArray(new String[0]))));
        } catch (Exception e) {
            return Mono.empty();
        }
    }
}
