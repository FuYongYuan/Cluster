package cn.fyy.gateway.config.security;

import cn.fyy.jwt.security.bean.ao.PermitUrl;
import cn.fyy.gateway.config.security.converter.JwtAuthConverter;
import cn.fyy.gateway.config.security.filter.JwtAuthenticationWebFilter;
import cn.fyy.gateway.config.security.handler.SecurityAccessDeniedHandler;
import cn.fyy.gateway.config.security.handler.SecurityAuthenticationEntryPoint;
import cn.fyy.gateway.config.security.matcher.JwtServerWebExchangeMatcher;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;

import java.util.List;

/**
 * @author fyy
 */
@Configuration
@EnableWebFluxSecurity
public class WebFluxSecurityConfig {
    /**
     * 过滤地址
     */
    @Resource
    private PermitUrl permitUrl;

    /**
     * 当访问接口没有权限时，自定义的返回结果
     */
    @Resource
    private SecurityAccessDeniedHandler securityAccessDeniedHandler;

    /**
     * 当未登录或者token失效访问接口时，自定义的返回结果
     */
    @Resource
    private SecurityAuthenticationEntryPoint securityAuthenticationEntryPoint;

    /**
     * 用户的校验逻辑
     */
    @Resource
    private ReactiveUserDetailsService reactiveUserDetailsServiceImpl;

    /**
     * jwt认证管理器
     */
    @Resource
    private JwtAuthConverter jwtAuthConverter;

    /**
     * 用于配置需要拦截的url路径、jwt过滤器及出异常后的处理器；
     */
    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        http
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                // 授权设置
                .authorizeExchange(exchange -> exchange
                        // 允许对于网站静态资源的无授权访问
                        .pathMatchers(permitUrl.getAll()).permitAll()
                        // 跨域请求会先进行一次options请求
                        .pathMatchers(HttpMethod.OPTIONS).permitAll()
                        // 除上面外的所有请求全部需要鉴权认证
                        .anyExchange().authenticated()
                )
                // 配置认证过滤器-顺序注意，需要在认证过滤器之前
                .addFilterAt(jwtAuthenticationWebFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
                // 添加自定义未授权和未登录结果返回
                .exceptionHandling((exceptions) -> exceptions
                        .authenticationEntryPoint(securityAuthenticationEntryPoint)
                        .accessDeniedHandler(securityAccessDeniedHandler)
                )
        ;
        return http.build();
    }

    /**
     * 认证管理
     *
     * @return 认证管理对象
     */
    @Bean
    public ReactiveAuthenticationManager authenticationManager() {
        return userDetailsRepositoryReactiveAuthenticationManager();
    }

    /**
     * 身份认证程序定义
     */
    @Bean
    public UserDetailsRepositoryReactiveAuthenticationManager userDetailsRepositoryReactiveAuthenticationManager() {
        // 注入UserDetailsService
        UserDetailsRepositoryReactiveAuthenticationManager reactiveAuthenticationManager
                = new UserDetailsRepositoryReactiveAuthenticationManager(reactiveUserDetailsServiceImpl);
        // 注入密码加密机制
        reactiveAuthenticationManager.setPasswordEncoder(passwordEncoder());
        return reactiveAuthenticationManager;
    }

    /**
     * SpringSecurity定义的用于对密码进行编码及比对的接口，
     * 目前使用的是BCryptPasswordEncoder；
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * JWT登录授权过滤器-不添加@Bean因为.addFilterAt(jwtAuthenticationWebFilter(), SecurityWebFiltersOrder.AUTHENTICATION)已经注入，使用@Bean会导致被执行两次
     */
    public AuthenticationWebFilter jwtAuthenticationWebFilter() {
        JwtAuthenticationWebFilter jwtAuthenticationWebFilter = new JwtAuthenticationWebFilter(authenticationManager());
        // 白名单
        List<String> whitelistPaths = List.of(permitUrl.getAll());
        // 拦截器设置
        jwtAuthenticationWebFilter.setServerAuthenticationConverter(jwtAuthConverter);
        jwtAuthenticationWebFilter.setRequiresAuthenticationMatcher(
                new JwtServerWebExchangeMatcher(whitelistPaths)
        );
        jwtAuthenticationWebFilter.setSecurityContextRepository(
                new WebSessionServerSecurityContextRepository()
        );
        return jwtAuthenticationWebFilter;
    }

}
