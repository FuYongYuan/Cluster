package cn.fyy.common.config.handler;

import cn.fyy.common.bean.ao.HttpStatusExplain;
import cn.fyy.common.bean.dto.ResultMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
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
     * 404
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

    /**
     * 405
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResultMessage<String> methodNotSupported(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage());
        return new ResultMessage<>(
                HttpStatus.METHOD_NOT_ALLOWED.value(),
                HttpStatusExplain.getChinese(HttpStatus.METHOD_NOT_ALLOWED)
        );
    }

    /**
     * 406
     */
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResultMessage<String> mediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException e) {
        log.error(e.getMessage());
        return new ResultMessage<>(
                HttpStatus.NOT_ACCEPTABLE.value(),
                HttpStatusExplain.getChinese(HttpStatus.NOT_ACCEPTABLE)
        );
    }

    /**
     * 415
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ResultMessage<String> mediaTypeNotSupported(HttpMediaTypeNotSupportedException e) {
        log.error(e.getMessage());
        return new ResultMessage<>(
                HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
                HttpStatusExplain.getChinese(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
        );
    }
}