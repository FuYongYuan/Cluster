package cn.fyy.gateway.config.security.service;


import cn.fyy.jwt.config.security.bean.bo.SecurityRedis;
import cn.fyy.jwt.config.security.bean.bo.SecurityUser;
import cn.fyy.redis.bean.ao.RedisSelect;
import cn.fyy.redis.service.RedisService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
     * 验证用户是否登录
     *
     * @param userId 用户ID
     * @return 登录用户信息
     * @throws UsernameNotFoundException 错误
     */

    @Override
    public Mono<UserDetails> findByUsername(String userId) {
        try {
            log.warn("ID:" + userId);
            SecurityRedis securityRedis = redisServiceImpl.get(RedisSelect.FIFTEEN, userId, SecurityRedis.class);
            if (securityRedis != null) {
                return Mono.just(new SecurityUser(
                        securityRedis.getManagerId(),
                        securityRedis.getManagerName(),
                        securityRedis.getUsername(),
                        securityRedis.getPassword(),
                        AuthorityUtils.createAuthorityList(securityRedis.getAuthorities())
                ));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}