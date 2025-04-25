package cn.fyy.gateway.config.security.handler;

import cn.fyy.common.bean.ao.SecurityHttpStatusChinese;
import cn.fyy.common.bean.dto.ResultMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * AuthenticationException 是在用户认证的时候出现错误时抛出的异常
 *
 * @author fuyy
 */
@Slf4j
@Component
public class SecurityAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {
    /**
     * 当接口没有登录返回处理
     *
     * @param exchange 请求返回信息
     * @param ex       错误信息
     */
    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException ex) {
        log.error("【用户认证的时候出现错误时抛出的异常】类:{}   方法:{}   行:{}   错误:{}",
                ex.getStackTrace()[0].getClassName(),
                ex.getStackTrace()[0].getMethodName(),
                ex.getStackTrace()[0].getLineNumber(),
                ex.getMessage()
        );

        return Mono.defer(() -> Mono.just(exchange.getResponse()).flatMap(response -> {
            ObjectMapper mapper = new ObjectMapper();
            // 没有访问权限
            ResultMessage<String> resultMessage = new ResultMessage<>(HttpStatus.UNAUTHORIZED.value(), SecurityHttpStatusChinese.getChineseDescriptionByHttpStatus(HttpStatus.UNAUTHORIZED));

            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

            DataBufferFactory dataBufferFactory = response.bufferFactory();
            DataBuffer dataBuffer;
            try {
                dataBuffer = dataBufferFactory.wrap(mapper.writeValueAsString(resultMessage).getBytes());
            } catch (JsonProcessingException e) {
                return Mono.error(new RuntimeException(e));
            }

            return response.writeWith(Mono.just(dataBuffer));
        }));
    }
}
