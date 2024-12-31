package cn.fyy.common.config.security;

import cn.fyy.common.config.security.filter.JwtAuthenticationWebFilter;
import cn.fyy.common.config.security.handler.SecurityAccessDeniedHandler;
import cn.fyy.common.config.security.handler.SecurityAuthenticationEntryPoint;
import cn.fyy.jwt.bean.ao.PermitUrl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.CacheControlHeadersWriter;

/**
 * @author fyy
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
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
     * 密码加密器
     */
    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * 用户的校验逻辑
     */
    @Resource
    private UserDetailsService userDetailsServiceImpl;

    /**
     * 用于配置需要拦截的url路径、jwt过滤器及出异常后的处理器；
     *
     * @return 配置HTTP安全性。
     * 禁用CSRF保护，因为我们使用的是JWT。
     * 配置会话管理策略为无状态（不创建HTTP session）。
     * 配置请求授权规则，允许特定路径访问，其他路径需要认证。
     * 添加JWT认证过滤器。
     * 配置异常处理，添加自定义的未授权和未登录结果返回处理器。
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf(AbstractHttpConfigurer::disable)
                // 基于token，所以不需要session,STATELESS不创建httpsession并不使用
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authorize) -> authorize
                        // 允许对于网站静态资源的无授权访问
                        .requestMatchers(
                                permitUrl.getAll()
                        ).permitAll()
                        // 跨域请求会先进行一次options请求
                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
                        // 除上面外的所有请求全部需要鉴权认证
                        .anyRequest().authenticated()
                )
                // 配置认证过滤器-顺序注意，需要在认证过滤器之前
                .addFilterBefore(jwtAuthenticationWebFilter(), UsernamePasswordAuthenticationFilter.class)
                // 添加自定义未授权和未登录结果返回
                .exceptionHandling((exceptions) -> exceptions
                        .authenticationEntryPoint(securityAuthenticationEntryPoint)
                        .accessDeniedHandler(securityAccessDeniedHandler)
                )
                // 禁用缓存
                .headers(headers -> headers.addHeaderWriter(new CacheControlHeadersWriter()))
        ;

        return http.build();
    }

    /**
     * 认证管理
     *
     * @return 认证管理对象
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(authenticationProvider());
    }

    /**
     * 配置DaoAuthenticationProvider
     *
     * @return AuthenticationProvider对象
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        // 关闭找不到用户错误
        authenticationProvider.setHideUserNotFoundExceptions(false);
        // 注入UserDetailsService
        authenticationProvider.setUserDetailsService(userDetailsServiceImpl);
        // 注入密码加密机制
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    /**
     * JWT登录授权过滤器
     */
    @Bean
    public JwtAuthenticationWebFilter jwtAuthenticationWebFilter() {
        return new JwtAuthenticationWebFilter();
    }

}
