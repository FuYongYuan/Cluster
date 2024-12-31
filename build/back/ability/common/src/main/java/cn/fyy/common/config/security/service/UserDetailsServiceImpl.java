package cn.fyy.common.config.security.service;


import cn.fyy.jwt.config.security.bean.bo.SecurityRedis;
import cn.fyy.jwt.config.security.bean.bo.SecurityUser;
import cn.fyy.redis.bean.ao.RedisSelect;
import cn.fyy.redis.service.RedisService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户验证逻辑层
 *
 * @author fuyy
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * Redis 业务类
     */
    @Resource
    private RedisService redisServiceImpl;

    /**
     * 验证用户是否登录
     *
     * @param redisId Redis中格式的用户ID
     * @return 登录用户信息
     * @throws UsernameNotFoundException 错误
     */
    @Override
    public UserDetails loadUserByUsername(String redisId) throws UsernameNotFoundException {
        try {
            log.info("UserDetailsService.loadUserByUsername(String redisId) 入参:{}", redisId);

            SecurityRedis securityRedis = redisServiceImpl.get(RedisSelect.FIFTEEN, redisId, SecurityRedis.class);
            if (securityRedis != null) {
                return new SecurityUser(
                        securityRedis.getManagerId(),
                        securityRedis.getManagerName(),
                        securityRedis.getUsername(),
                        securityRedis.getPassword(),
                        AuthorityUtils.createAuthorityList(securityRedis.getAuthorities())
                );
            } else {
                throw new UsernameNotFoundException("【未能读取到用户已登陆信息】");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new UsernameNotFoundException("【读取用户已登陆信息时发生错误】");
        }
    }
}