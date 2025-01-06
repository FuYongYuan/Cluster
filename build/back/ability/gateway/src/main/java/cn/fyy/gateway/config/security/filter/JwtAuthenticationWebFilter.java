package cn.fyy.gateway.config.security.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * JWT认证过滤器
 *
 * @author fyy
 */
@Slf4j
public class JwtAuthenticationWebFilter extends AuthenticationWebFilter {

    public JwtAuthenticationWebFilter(ReactiveAuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        log.info("{}被执行，访问地址：{}", this.getClass().getName(), exchange.getRequest().getPath());

        // 可以在这里添加自定义逻辑
        return super.filter(exchange, chain);
    }

    @Override
    protected Mono<Void> onAuthenticationSuccess(Authentication authentication, WebFilterExchange webFilterExchange) {
        log.info("认证通过: {}", authentication);
        return super.onAuthenticationSuccess(authentication, webFilterExchange);
    }
}
