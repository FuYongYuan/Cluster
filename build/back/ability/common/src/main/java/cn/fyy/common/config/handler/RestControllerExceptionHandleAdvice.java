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

        switch (e) {
            case BusinessException exception ->
                    log.error("服务器忙,请稍后再试\n{}", ConstantService.logMessage(exception));
            case NullPointerException exception ->
                    log.error("发生空指针异常\n{}", ConstantService.logMessage(exception));
            case NoSuchMethodException exception ->
                    log.error("未能找到该执行的方法\n{}", ConstantService.logMessage(exception));
            case IllegalArgumentException exception ->
                    log.error("请求参数类型不匹配\n{}", ConstantService.logMessage(exception));
            default -> log.error("服务器代码发生异常,请联系管理员");
        }

        return new ResultMessage<>(500, "服务器忙,请稍后再试");
    }
}