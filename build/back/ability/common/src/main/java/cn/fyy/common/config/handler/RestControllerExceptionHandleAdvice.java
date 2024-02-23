package cn.fyy.common.config.handler;

import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.common.service.ConstantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * RestController 错误处理
 *
 * @author fuyy
 */
@Slf4j
@RestControllerAdvice
public class RestControllerExceptionHandleAdvice {
    /**
     * 错误处理
     *
     * @param e 报错信息
     * @return 返回给前端的信息
     */
    @ExceptionHandler
    public ResultMessage<String> handler(Exception e) {
        log.error(ConstantService.logMessage(e));

        return switch (e) {
            case BusinessException businessException -> new ResultMessage<>(500, "服务器忙,请稍后再试");
            case NullPointerException nullPointerException -> new ResultMessage<>(500, "发生空指针异常");
            case NoSuchMethodException noSuchMethodException -> new ResultMessage<>(500, "未能找到该执行的方法");
            case IllegalArgumentException illegalArgumentException -> new ResultMessage<>(500, "请求参数类型不匹配");
            default -> new ResultMessage<>(500, "服务器代码发生异常,请联系管理员");
        };
    }
}