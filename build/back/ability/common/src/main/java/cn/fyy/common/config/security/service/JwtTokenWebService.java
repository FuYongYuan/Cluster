package cn.fyy.common.config.security.service;

import cn.fyy.jwt.config.jwt.JwtProperties;
import cn.fyy.jwt.service.JwtTokenService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * JWT工具类
 *
 * @author fuyy
 */
@Slf4j
@Service
public class JwtTokenWebService extends JwtTokenService {

    /**
     * 快速获取Request中的Token
     */
    public String getTokenFromRequest(HttpServletRequest request) {
        // 从请求中提取JWT Token
        String token = request.getHeader(jwtProperties.getTokenHeader());
        if (StringUtils.hasText(token) && token.startsWith(jwtProperties.getTokenHead() + " ")) {
            return token.substring(jwtProperties.getTokenHead().length());
        }
        return null;
    }

    /**
     * 获取jwtProperties
     */
    public JwtProperties getJwtProperties() {
        return jwtProperties;
    }
}
