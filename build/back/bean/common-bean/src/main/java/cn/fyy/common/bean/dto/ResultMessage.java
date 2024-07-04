package cn.fyy.common.bean.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 请求结果返回消息类
 *
 * @author fuyy
 */
@Schema(name = "ResultMessage<T>", description = "统一返回对象")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultMessage<T> implements Serializable {
    /**
     * 状态码
     */
    @Schema(name = "code", description = "状态码", type = "Integer")
    private Integer code;
    /**
     * 数据内容
     */
    @Schema(name = "data", description = "数据内容", type = "T", nullable = true)
    private T data;
    /**
     * 状态码描述
     */
    @Schema(name = "message", description = "状态码描述", type = "String", nullable = true)
    private String message;

    /**
     * 常用正确返回
     *
     * @param data 数据内容
     */
    public ResultMessage(T data) {
        this.code = HttpStatus.OK.value();
        this.data = data;
        this.message = "请求成功!";
    }

    /**
     * 常用错误返回
     *
     * @param code    状态码
     * @param message 状态码描述
     */
    public ResultMessage(Integer code, String message) {
        this.code = code;
        this.data = null;
        this.message = message;
    }
}
