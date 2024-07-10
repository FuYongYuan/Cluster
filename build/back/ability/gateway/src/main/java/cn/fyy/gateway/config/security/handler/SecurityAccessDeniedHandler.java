package cn.fyy.gateway.config.security.handler;

import cn.fyy.common.bean.ao.HttpStatusExplain;
import cn.fyy.common.bean.dto.ResultMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;

/**
 * AccessDeniedException 主要是在用户在访问受保护资源时被拒绝而抛出的异常
 *
 * @author fuyy
 */
@Slf4j
@Component
public class SecurityAccessDeniedHandler implements ServerAccessDeniedHandler {
    /**
     * 当接口没有权限返回处理
     *
     * @param exchange 请求返回信息
     * @param ex       错误信息
     */
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException ex) {
        log.error("【用户在访问受保护资源时被拒绝而抛出的异常】类:{}   方法:{}   行:{}   错误:{}",
                ex.getStackTrace()[0].getClassName(),
                ex.getStackTrace()[0].getMethodName(),
                ex.getStackTrace()[0].getLineNumber(),
                ex.getMessage()
        );

        return Mono.defer(() -> Mono.just(exchange.getResponse())).flatMap(response -> {
            ObjectMapper mapper = new ObjectMapper();
            // 没有访问权限
            ResultMessage<String> resultMessage = new ResultMessage<>(HttpStatus.FORBIDDEN.value(), HttpStatusExplain.getSecurityStatus(HttpStatus.FORBIDDEN));

            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

            DataBufferFactory dataBufferFactory = response.bufferFactory();
            String result;
            try {
                result = mapper.writeValueAsString(resultMessage);
            } catch (JsonProcessingException e) {
                return Mono.error(new RuntimeException(e));
            }
            DataBuffer buffer = dataBufferFactory.wrap(result.getBytes(Charset.defaultCharset()));

            return response.writeWith(Mono.just(buffer));
        });
    }
}
