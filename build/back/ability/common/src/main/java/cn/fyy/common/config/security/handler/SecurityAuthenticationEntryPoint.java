package cn.fyy.common.config.security.handler;

import cn.fyy.common.bean.ao.HttpStatusExplain;
import cn.fyy.common.bean.dto.ResultMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * AuthenticationException 是在用户认证的时候出现错误时抛出的异常
 *
 * @author fuyy
 */
@Slf4j
@Component
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /**
     * 当接口没有登录返回处理
     *
     * @param request   请求信息
     * @param response  返回信息
     * @param exception 错误信息
     * @throws IOException 错误
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.error("【用户认证的时候出现错误时抛出的异常】类:{}   方法:{}   行:{}   错误:{}",
                exception.getStackTrace()[0].getClassName(),
                exception.getStackTrace()[0].getMethodName(),
                exception.getStackTrace()[0].getLineNumber(),
                exception.getMessage()
        );
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        // 没有访问权限
        ResultMessage<String> resultMessage = new ResultMessage<>(HttpStatus.UNAUTHORIZED.value(), HttpStatusExplain.getSecurityStatus(HttpStatus.UNAUTHORIZED));
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().println(mapper.writeValueAsString(resultMessage));
        response.getWriter().flush();
        throw exception;
    }
}
