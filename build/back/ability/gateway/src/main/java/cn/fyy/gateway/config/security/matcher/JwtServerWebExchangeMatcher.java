package cn.fyy.gateway.config.security.matcher;

import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * Jwt过滤匹配器
 *
 * @author fyy
 */
public class JwtServerWebExchangeMatcher implements ServerWebExchangeMatcher {

    /**
     * 不过滤白名单
     */
    private final List<ServerWebExchangeMatcher> whitelistMatchers;

    /**
     * 所有
     */
    private final ServerWebExchangeMatcher allPathsMatcher;

    /**
     * 构造函数
     *
     * @param whitelistedPaths 白名单
     */
    public JwtServerWebExchangeMatcher(List<String> whitelistedPaths) {
        this.whitelistMatchers = new ArrayList<>();
        for (String path : whitelistedPaths) {
            whitelistMatchers.add(ServerWebExchangeMatchers.pathMatchers(path));
        }
        this.allPathsMatcher = ServerWebExchangeMatchers.pathMatchers("/**");
    }

    /**
     * 判断请求是否需要进行JWT验证
     *
     * @param exchange 当前的HTTP请求交换信息
     * @return 匹配结果，如果请求在白名单内则不需要验证
     */
    @Override
    public Mono<MatchResult> matches(ServerWebExchange exchange) {
        // 使用any方法检查请求是否匹配任何白名单路径
        return Flux.fromIterable(whitelistMatchers)
                .concatMap(matcher -> matcher.matches(exchange))
                .any(MatchResult::isMatch)
                // 根据白名单匹配结果返回相应的MatchResult
                .flatMap(isWhiteListed -> isWhiteListed ? MatchResult.notMatch() : allPathsMatcher.matches(exchange));
    }
}
