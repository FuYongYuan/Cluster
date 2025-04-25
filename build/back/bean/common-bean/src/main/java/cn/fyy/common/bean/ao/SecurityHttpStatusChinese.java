package cn.fyy.common.bean.ao;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 登录状态枚举
 *
 * @author fyy
 */
@Getter
public enum SecurityHttpStatusChinese {
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "用户未登录或登录信息已过期，请检查登录信息！"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "当前接口您没有权限访问，请检查用户权限！");

    /**
     * http状态码
     */
    private final HttpStatus httpStatus;
    /**
     * 中文描述
     */
    private final String chineseDescription;

    /**
     * 构造器
     *
     * @param httpStatus         http状态码
     * @param chineseDescription 中文描述
     */
    SecurityHttpStatusChinese(HttpStatus httpStatus, String chineseDescription) {
        this.httpStatus = httpStatus;
        this.chineseDescription = chineseDescription;
    }

    /**
     * 根据http状态码获取中文描述
     *
     * @param httpStatus http状态码
     * @return 中文描述
     */
    public static String getChineseDescriptionByHttpStatus(HttpStatus httpStatus) {
        for (SecurityHttpStatusChinese status : SecurityHttpStatusChinese.values()) {
            if (status.getHttpStatus() == httpStatus) {
                return status.getChineseDescription();
            }
        }
        return null;
    }
}
