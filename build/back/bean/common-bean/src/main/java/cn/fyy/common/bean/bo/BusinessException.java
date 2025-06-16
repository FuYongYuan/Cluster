package cn.fyy.common.bean.bo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * 错误处理类
 *
 * @author fuyy
 */
@Slf4j
public class BusinessException extends Exception implements Serializable {

    /**
     * 默认错误信息
     */
    private static final String DEFAULT_ERROR_MSG = "操作失败,请稍后重试";

    /**
     * 错误内容
     *
     * @param message 信息内容
     */
    public BusinessException(String message) {
        super(StringUtils.hasText(message) ? message : DEFAULT_ERROR_MSG);
        log.error("错误内容{}", message);
    }

    /**
     * 错误对象
     *
     * @param exception 错误对象
     */
    public BusinessException(Exception exception) {
        super(DEFAULT_ERROR_MSG, exception);
        log.error("错误内容", exception);
    }

    /**
     * 错误内容+对象
     *
     * @param message   信息内容
     * @param exception 错误对象
     */
    public BusinessException(String message, Exception exception) {
        super(StringUtils.hasText(message) ? message : DEFAULT_ERROR_MSG, exception);
        log.error("错误内容", exception);
    }

    /**
     * 错误初始化
     */
    public BusinessException() {
        super(DEFAULT_ERROR_MSG);
    }
}
