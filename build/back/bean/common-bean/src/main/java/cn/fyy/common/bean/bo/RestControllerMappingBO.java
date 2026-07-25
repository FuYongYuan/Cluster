package cn.fyy.common.bean.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RestController Mapping信息
 *
 * @author fyy
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class RestControllerMappingBO {
    /**
     * 类名
     */
    private String className;

    /**
     * 类Mapping值
     */
    private String classMapping;

    /**
     * 类Swagger说明
     */
    private String classExplain;
    /**
     * 方法名
     */
    private String methodName;

    /**
     * 方法Mapping值
     */
    private String methodMapping;

    /**
     * 方法Swagger说明
     */
    private String methodExplain;
}
