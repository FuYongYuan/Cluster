package cn.fyy.authorization.bean.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 登录信息
 *
 * @author fuyy
 */
@Schema(name = "JwtDTO", description = "登录信息 实体类")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class JwtDTO implements Serializable {
    /**
     * 账号
     */
    @Schema(name = "account", description = "账号", type = "String")
    private String account;

    /**
     * 姓名
     */
    @Schema(name = "managerName", description = "姓名", type = "String")
    private String managerName;

    /**
     * 头像
     */
    @Schema(name = "headImgUrl", description = "头像", type = "String")
    private String headImgUrl;

    /**
     * jwtToken
     */
    @Schema(name = "jwtToken", description = "Token", type = "String")
    private String jwtToken;

    /**
     * 失效时间
     */
    @Schema(name = "invalidDate", description = "失效时间", type = "LocalDateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime invalidDate;
}
