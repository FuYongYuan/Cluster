package cn.fyy.common.config.security.filter;

import cn.fyy.common.bean.ao.ConstantParameter;
import cn.fyy.common.bean.ao.SecurityHttpStatusChinese;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.common.config.security.service.JwtTokenWebService;
import cn.fyy.jwt.config.security.bean.bo.SecurityUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWT认证过滤器
 *
 * @author fyy
 */
@Slf4j
public class JwtAuthenticationWebFilter extends OncePerRequestFilter {
    /**
     * 用户验证逻辑层
     */
    @Resource
    private UserDetailsService userDetailsServiceImpl;

    /**
     * JWT 工具类
     */
    @Resource
    private JwtTokenWebService jwtTokenWebService;

    //------------------------------------------------------------------------------------------------------------------拦截方法

    /**
     * 拦截后动作
     *
     * @param request     请求信息
     * @param response    返回信息
     * @param filterChain 拦截后链路
     * @throws ServletException 错误
     * @throws IOException      错误
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        log.info("{}被执行，访问地址：{}", this.getClass().getName(), request.getRequestURL().toString());

        // 可以在这里添加自定义逻辑
        String header = request.getHeader(jwtTokenWebService.getJwtProperties().getTokenHeader());
        if (header != null && header.startsWith(jwtTokenWebService.getJwtProperties().getTokenHead())) {
            // 仅处理需要认证的请求
            String jwt = jwtTokenWebService.getTokenFromRequest(request);
            if (StringUtils.hasText(jwt) && jwtTokenWebService.validateToken(jwt)) {
                Long managerId = jwtTokenWebService.getManagerIdFromToken(jwt);
                try {
                    SecurityUser userDetails = (SecurityUser) userDetailsServiceImpl.loadUserByUsername(ConstantParameter.MANAGER_JWT_INFO_KEY + managerId);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } catch (UsernameNotFoundException ex) {
                    ResultMessage<String> resultMessage = new ResultMessage<>(HttpStatus.UNAUTHORIZED.value(), SecurityHttpStatusChinese.getChineseDescriptionByHttpStatus(HttpStatus.UNAUTHORIZED));
                    this.returnErrorMessage(response, resultMessage);
                    return;
                }
            } else {
                ResultMessage<String> resultMessage = new ResultMessage<>(HttpStatus.UNAUTHORIZED.value(), SecurityHttpStatusChinese.getChineseDescriptionByHttpStatus(HttpStatus.UNAUTHORIZED));
                this.returnErrorMessage(response, resultMessage);
                return;
            }
        }
        // 继续调用filter链
        filterChain.doFilter(request, response);
    }

    //------------------------------------------------------------------------------------------------------------------私有方法

    /**
     * 返回错误信息
     *
     * @param response      返回信息
     * @param resultMessage 错误信息
     * @throws IOException 错误
     */
    private void returnErrorMessage(HttpServletResponse response, ResultMessage<String> resultMessage) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(mapper.writeValueAsString(resultMessage));
        response.getWriter().flush();
    }
}
