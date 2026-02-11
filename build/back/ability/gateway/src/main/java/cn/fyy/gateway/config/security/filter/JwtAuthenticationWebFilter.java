package cn.fyy.gateway.config.security.filter;

import cn.fyy.jwt.bean.bo.SecurityAuthority;
import cn.fyy.jwt.bean.bo.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * JWT 认证过滤器
 *
 * @author fyy
 */
@Slf4j
public class JwtAuthenticationWebFilter extends AuthenticationWebFilter {

    /**
     * 构造方法
     *
     * @param authenticationManager 认证管理器
     */
    public JwtAuthenticationWebFilter(ReactiveAuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /**
     * 过滤器执行方法
     *
     * @param exchange 请求
     * @param chain    过滤器链
     * @return 响应
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        log.info("{}被执行，访问地址：{}", this.getClass().getName(), exchange.getRequest().getPath());

        // 可以在这里添加自定义逻辑
        return super.filter(exchange, chain);
    }

    /**
     * 认证成功处理方法
     *
     * @param authentication    认证信息
     * @param webFilterExchange 请求
     * @return 响应
     */
    @Override
    protected Mono<Void> onAuthenticationSuccess(Authentication authentication, WebFilterExchange webFilterExchange) {
        log.info("认证开始: {}", authentication);
        List<SecurityAuthority> authorities = authentication.getAuthorities().stream()
                .map(
                        grantedAuthority -> {
                            String authority = grantedAuthority.getAuthority();
                            if (authority.startsWith("ROLE_")) {
                                // 移除 "ROLE_" 前缀
                                authority = authority.substring(5);
                            }
                            return new SecurityAuthority(authority);
                        }
                )
                .toList();
        //校验权限中是否含盖本次请求的权限
        String requestPath = webFilterExchange.getExchange().getRequest().getPath().value();

        // 使用 AntPathMatcher 进行路径匹配
        AntPathMatcher pathMatcher = new AntPathMatcher();
        boolean matches = pathMatcher.match("/capability/menu/query/{currentPage}/{eachPageSize}", requestPath);

        if (matches) {
            log.info("请求路径 {} 符合 /capability/menu/query/{currentPage}/{eachPageSize} 模式", requestPath);
            // 在这里添加你想要执行的逻辑
        } else {
            log.info("请求路径 {} 不符合 /capability/menu/query/{currentPage}/{eachPageSize} 模式", requestPath);
        }

        log.info("认证权限: {}", authorities);
        log.info("认证地址: {}", requestPath);
        return super.onAuthenticationSuccess(authentication, webFilterExchange);
    }
}
