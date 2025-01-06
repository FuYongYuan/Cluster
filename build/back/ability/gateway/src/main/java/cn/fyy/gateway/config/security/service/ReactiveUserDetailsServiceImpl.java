package cn.fyy.gateway.config.security.service;

import cn.fyy.common.bean.ao.ConstantParameter;
import cn.fyy.jwt.config.security.bean.bo.SecurityRedis;
import cn.fyy.jwt.config.security.bean.bo.SecurityUser;
import cn.fyy.redis.bean.ao.RedisSelect;
import cn.fyy.redis.service.RedisService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * 用户验证逻辑层
 *
 * @author fuyy
 */
@Slf4j
@Service
public class ReactiveUserDetailsServiceImpl implements ReactiveUserDetailsService {

    /**
     * Redis 业务类
     */
    @Resource
    private RedisService redisServiceImpl;

    /**
     * 密码加密器
     */
    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * 验证用户是否登录
     *
     * @param managerId 用户ID-来自于JwtAuthConverter中createAuthentication方法
     * @return 登录用户信息
     * @throws UsernameNotFoundException 错误
     */

    @Override
    public Mono<UserDetails> findByUsername(String managerId) {
        log.info("ReactiveUserDetailsService.findByUsername(String managerId) 入参:{}，结果:{}", managerId, ConstantParameter.MANAGER_JWT_INFO_KEY + managerId);
        return Mono.fromCallable(() -> redisServiceImpl.get(RedisSelect.FIFTEEN, ConstantParameter.MANAGER_JWT_INFO_KEY + managerId, SecurityRedis.class))
                .flatMap(securityRedis -> {
                    if (securityRedis != null) {
                        SecurityUser securityUser = new SecurityUser(
                                securityRedis.getManagerId(),
                                securityRedis.getManagerName(),
                                securityRedis.getUsername(),
                                passwordEncoder.encode(securityRedis.getPassword()),
                                AuthorityUtils.createAuthorityList(securityRedis.getAuthorities())
                        );
                        UserDetails ud = User
                                .withUsername(String.valueOf(securityUser.getManagerId()))
                                .password(securityUser.getPassword())
                                .roles(securityRedis.getAuthorities())
                                .build();
                        return Mono.just(ud);
                    }
                    return Mono.error(new UsernameNotFoundException("管理员ID不存在: " + managerId));
                })
                .doOnError(e -> log.error("查询管理员ID的用户详细信息时出错: {}", managerId, e));
    }
}