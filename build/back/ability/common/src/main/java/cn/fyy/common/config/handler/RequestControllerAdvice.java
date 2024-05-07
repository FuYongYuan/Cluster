package cn.fyy.common.config.handler;

import cn.fyy.common.bean.ao.HttpStatusExplain;
import cn.fyy.common.bean.dto.ResultMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;


/**
 * 错误处理
 *
 * @author fuyy
 */
@Slf4j
@ControllerAdvice
public class RequestControllerAdvice {
    /**
     * 404 请求处理
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResultMessage<String> methodNotSupported(NoHandlerFoundException e) {
        log.error(e.getMessage());
        return new ResultMessage<>(
                HttpStatus.NOT_FOUND.value(),
                HttpStatusExplain.getChinese(HttpStatus.NOT_FOUND)
        );
    }
}