package cn.fyy.gateway.config.security.converter;

import cn.fyy.jwt.service.JwtTokenService;
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
    /**
     * JWT 工具类
     */
    @Resource
    private JwtTokenService jwtTokenService;

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
            // 解析 JWT 获取 managerId 和权限
            String managerId = String.valueOf(jwtTokenService.getManagerIdFromToken(token));
            String password = String.valueOf(jwtTokenService.getManagerPasswordFromToken(token));
            List<String> authorities = jwtTokenService.getAuthoritiesFromToken(token);
            // 使用 managerId 作为 Principal
            return Mono.just(new UsernamePasswordAuthenticationToken(
                    managerId,
                    password,
                    AuthorityUtils.createAuthorityList(authorities)
            ));
        } catch (Exception e) {
            log.error("解析 JWT 失败: {}", e.getMessage(), e);
            return Mono.empty();
        }
    }
}
