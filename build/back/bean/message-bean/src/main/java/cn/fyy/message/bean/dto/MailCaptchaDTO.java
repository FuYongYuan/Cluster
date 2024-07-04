package cn.fyy.message.bean.dto;

import cn.fyy.message.bean.bo.MailCaptchaBO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 邮箱验证码
 *
 * @author fuyy
 */
@Schema(name = "MailCaptchaDTO", description = "邮箱验证码 实体类")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MailCaptchaDTO implements Serializable {
    /**
     * 缓存名称
     */
    @Schema(name = "captchaCacheName", description = "缓存名称", type = "String")
    private String captchaCacheName;

    //------------------------------------------------------------------------------------------------------------------转换

    /**
     * 公告 BO 转换 DTO
     */
    public static MailCaptchaDTO toDTO(MailCaptchaBO bo) {
        if (bo != null) {
            return MailCaptchaDTO.builder()
                    .captchaCacheName(bo.getCaptchaCacheName())
                    .build();
        } else {
            return null;
        }
    }

    /**
     * 公告 BO 数组 转换 DTO List
     */
    public static List<MailCaptchaDTO> toDTO(MailCaptchaBO[] bos) {
        if (bos != null) {
            List<MailCaptchaDTO> dtos = new ArrayList<>();
            for (MailCaptchaBO bo : bos) {
                dtos.add(toDTO(bo));
            }
            return dtos;
        } else {
            return null;
        }
    }

    /**
     * 公告 BO List 转换 DTO List
     */
    public static List<MailCaptchaDTO> toDTO(List<MailCaptchaBO> bos) {
        if (bos != null) {
            List<MailCaptchaDTO> dtos = new ArrayList<>();
            for (MailCaptchaBO bo : bos) {
                dtos.add(toDTO(bo));
            }
            return dtos;
        } else {
            return null;
        }
    }

    /**
     * 公告 DTO 转换 BO
     */
    public static MailCaptchaBO toBO(MailCaptchaDTO dto) {
        if (dto != null) {
            return MailCaptchaBO.builder()
                    .captchaCacheName(dto.getCaptchaCacheName())
                    .build();
        } else {
            return null;
        }
    }

    /**
     * 公告 DTO List 转换 BO List
     */
    public static List<MailCaptchaBO> toBO(List<MailCaptchaDTO> dtos) {
        if (dtos != null) {
            List<MailCaptchaBO> bos = new ArrayList<>();
            for (MailCaptchaDTO dto : dtos) {
                bos.add(toBO(dto));
            }
            return bos;
        } else {
            return null;
        }
    }

    /**
     * 公告 DTO 转换 BO
     */
    public MailCaptchaBO toBO() {
        return MailCaptchaBO.builder()
                .captchaCacheName(this.getCaptchaCacheName())
                .build();
    }
}
