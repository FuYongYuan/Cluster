package cn.fyy.jwt.service;

import cn.fyy.common.bean.ao.ConstantParameter;
import cn.fyy.jwt.config.jwt.JwtProperties;
import cn.fyy.jwt.config.security.bean.bo.ManagerMessage;
import cn.fyy.jwt.config.security.bean.bo.SecurityUser;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.time.Instant;
import java.util.*;

/**
 * JWT工具类
 *
 * @author fuyy
 */
@Slf4j
@Service
public class JwtTokenService {
    /**
     * JWT参数
     */
    @Resource
    protected JwtProperties jwtProperties;

    /**
     * JWT解码器
     */
    @Resource
    protected JwtDecoder jwtDecoder;

    /**
     * JWT编码器
     */
    @Resource
    protected JwtEncoder jwtEncoder;

    /**
     * 生成 access_token
     *
     * @param subject      代表这个JWT的主体，即它的所有人 一般是用户id
     * @param securityUser 存储在JWT里面的信息 一般放些用户的权限/角色信息
     * @return java.lang.String
     */
    public String getAccessToken(String subject, SecurityUser securityUser) {
        return generateToken(jwtProperties.getIssuer(), subject, securityUser, jwtProperties.getAccessTokenExpireTime().toMillis());
    }

    /**
     * 签发token
     *
     * @param issuer        签发系统
     * @param subject       代表这个JWT的主体，即它的所有人 一般是用户id
     * @param securityUser  存储在JWT里面的信息 一般放些用户的权限/角色信息
     * @param expireAppTime 有效时间(毫秒)
     * @return java.lang.String
     */
    public String generateToken(String issuer, String subject, SecurityUser securityUser, long expireAppTime) {
        Instant now = Instant.now();
        Instant expires = expireAppTime >= 0
                ? now.plusSeconds(expireAppTime)
                : now.plusSeconds(ConstantParameter.JWT_EXPIRATION_REFRESH);

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", securityUser.getUsername());
        claims.put("authorities", securityUser.getAuthorities());
        // 根据需要从SecurityUser添加其他字段
        Class<?> clazz = securityUser.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                claims.put(field.getName(), field.get(securityUser));
            } catch (IllegalAccessException e) {
                throw new RuntimeException("访问字段失败: " + field.getName(), e);
            }
        }

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .id(securityUser.getManagerId().toString())
                .subject(subject)
                .issuer(issuer)
                .issuedAt(now)
                .expiresAt(expires)
                .claims(existingClaims -> existingClaims.putAll(claims))
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
    }

    /**
     * 获取管理员账号
     *
     * @return java.lang.String
     */
    public String getUserNameFromToken(String token) {
        String userName = null;
        if (StringUtils.hasText(token)) {
            try {
                Jwt jwt = getJwtFromToken(token);
                if (jwt != null) {
                    userName = jwt.getSubject();
                }
            } catch (Exception e) {
                log.error("获取管理员账号", e);
            }
        }
        return userName;
    }

    /**
     * 获取管理员权限
     *
     * @return java.lang.String
     */
    public List<String> getAuthoritiesFromToken(String token) {
        List<String> authorities = List.of();
        if (StringUtils.hasText(token)) {
            try {
                Jwt jwt = getJwtFromToken(token);
                if (jwt != null) {
                    authorities = jwt.getClaimAsStringList("authorities");
                }
            } catch (Exception e) {
                log.error("获取管理员账号", e);
            }
        }
        return authorities;
    }

    /**
     * 从令牌中获取数据声明
     *
     * @return io.jsonwebtoken.Claims
     */
    public BigInteger getManagerIdFromToken(String token) {
        if (StringUtils.hasText(token)) {
            Map<String, Object> claims;
            try {
                Jwt jwt = jwtDecoder.decode(token);
                claims = jwt.getClaims();
                if (claims != null && !claims.isEmpty()) {
                    return new BigInteger(String.valueOf(claims.get(ConstantParameter.JWT_MANAGER_ID)));
                }
            } catch (Exception e) {
                log.error("获取用户ID", e);
            }
        }
        return null;
    }

    /**
     * 从令牌中获取数据声明
     *
     * @return io.jsonwebtoken.Claims
     */
    public String getManagerNameFromToken(String token) {
        if (StringUtils.hasText(token)) {
            Map<String, Object> claims;
            try {
                Jwt jwt = jwtDecoder.decode(token);
                claims = jwt.getClaims();
                if (claims != null && !claims.isEmpty()) {
                    return String.valueOf(claims.get(ConstantParameter.JWT_MANAGER_NAME));
                }
            } catch (Exception e) {
                log.error("获取用户ID", e);
            }
        }
        return null;
    }

    /**
     * 从令牌中获取数据声明
     *
     * @return io.jsonwebtoken.Claims
     */
    public ManagerMessage getManagerMessageFromToken(String token) {
        if (StringUtils.hasText(token)) {
            Map<String, Object> claims;
            try {
                Jwt jwt = jwtDecoder.decode(token);
                claims = jwt.getClaims();
                if (claims != null && !claims.isEmpty()) {
                    return ManagerMessage.builder()
                            .managerId(new BigInteger(String.valueOf(claims.get(ConstantParameter.JWT_MANAGER_ID))))
                            .managerName(String.valueOf(claims.get(ConstantParameter.JWT_MANAGER_NAME)))
                            .build();
                }
            } catch (Exception e) {
                log.error("获取用户信息", e);
            }
        }
        return null;
    }

    /**
     * 从令牌中获取数据声明
     *
     * @return io.jsonwebtoken.Claims
     */
    public Jwt getJwtFromToken(String token) {
        try {
            if (StringUtils.hasText(token)) {
                return jwtDecoder.decode(token);
            }
        } catch (Exception e) {
            log.error("从令牌中获取数据声明", e);
        }
        return null;
    }

    /**
     * 校验令牌
     *
     * @return java.lang.Boolean
     */
    public Boolean validateToken(String token) {
        if (StringUtils.hasText(token)) {
            Jwt jwt = getJwtFromToken(token);
            return (null != jwt && !isTokenExpired(token));
        } else {
            return true;
        }
    }

    /**
     * 验证token 是否过期
     *
     * @return java.lang.Boolean
     */
    public Boolean isTokenExpired(String token) {
        if (StringUtils.hasText(token)) {
            try {
                Jwt jwt = getJwtFromToken(token);
                Instant instant = jwt.getExpiresAt();
                if (instant != null) {
                    return instant.isBefore(new Date().toInstant());
                }
                return true;
            } catch (Exception e) {
                log.error("验证是否过期", e);
                return true;
            }
        } else {
            return true;
        }
    }

    /**
     * 获取token的剩余过期时间
     *
     * @return long
     */
    public long getRemainingTime(String token) {
        long result = 0;
        try {
            long nowMillis = System.currentTimeMillis();
            result = Objects.requireNonNull(getJwtFromToken(token).getExpiresAt()).minusMillis(nowMillis).toEpochMilli();
        } catch (Exception e) {
            log.error("获取剩余过期时间", e);
        }
        return result;
    }

}