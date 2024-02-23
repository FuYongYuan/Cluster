package cn.fyy.common.bean.bo;

import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * 错误处理类
 *
 * @author fuyy
 */
public class BusinessException extends Exception implements Serializable {

    private static final String DEFAULT_ERROR_MSG = "操作失败,请稍后重试";

    public BusinessException(String message) {
        super(StringUtils.hasText(message) ? message : DEFAULT_ERROR_MSG);
    }

    public BusinessException(Exception exception) {
        super(DEFAULT_ERROR_MSG, exception);
    }

    public BusinessException(String message, Exception exception) {
        super(StringUtils.hasText(message) ? message : DEFAULT_ERROR_MSG, exception);
    }

    public BusinessException() {
        super(DEFAULT_ERROR_MSG);
    }
}
