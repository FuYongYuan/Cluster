package cn.fyy.common.config.feign;

import cn.fyy.jwt.security.bean.SecurityJwtProperties;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Feign配置
 * 使用FeignClient进行服务间调用，传递headers信息
 *
 * @author fuyy
 */
@Configuration
public class FeignConfig implements RequestInterceptor {
    /**
     * JWT参数
     */
    @Resource
    private SecurityJwtProperties securityProperties;

    /**
     * 设置携带的Header
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            // 添加认证信息
            requestTemplate.header(securityProperties.getTokenHeader(), request.getHeader(securityProperties.getTokenHeader()));
        }
    }
}