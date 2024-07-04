package cn.fyy.message.bean.dto;

import cn.fyy.message.bean.bo.ImageCaptchaBO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 图形验证码
 *
 * @author fuyy
 */
@Schema(name = "ImageCaptchaDTO", description = "图形验证码 实体类")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ImageCaptchaDTO implements Serializable {
    /**
     * 缓存名称
     */
    @Schema(name = "captchaCacheName", description = "缓存名称", type = "String")
    private String captchaCacheName;

    /**
     * 图片数据
     */
    @Schema(name = "imageData", description = "图片数据", type = "String")
    private String imageData;

    //------------------------------------------------------------------------------------------------------------------转换

    /**
     * 公告 BO 转换 DTO
     */
    public static ImageCaptchaDTO toDTO(ImageCaptchaBO bo) {
        if (bo != null) {
            return ImageCaptchaDTO.builder()
                    .captchaCacheName(bo.getCaptchaCacheName())
                    .imageData(bo.getImageData())
                    .build();
        } else {
            return null;
        }
    }

    /**
     * 公告 BO 数组 转换 DTO List
     */
    public static List<ImageCaptchaDTO> toDTO(ImageCaptchaBO[] bos) {
        if (bos != null) {
            List<ImageCaptchaDTO> dtos = new ArrayList<>();
            for (ImageCaptchaBO bo : bos) {
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
    public static List<ImageCaptchaDTO> toDTO(List<ImageCaptchaBO> bos) {
        if (bos != null) {
            List<ImageCaptchaDTO> dtos = new ArrayList<>();
            for (ImageCaptchaBO bo : bos) {
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
    public static ImageCaptchaBO toBO(ImageCaptchaDTO dto) {
        if (dto != null) {
            return ImageCaptchaBO.builder()
                    .captchaCacheName(dto.getCaptchaCacheName())
                    .imageData(dto.getImageData())
                    .build();
        } else {
            return null;
        }
    }

    /**
     * 公告 DTO List 转换 BO List
     */
    public static List<ImageCaptchaBO> toBO(List<ImageCaptchaDTO> dtos) {
        if (dtos != null) {
            List<ImageCaptchaBO> bos = new ArrayList<>();
            for (ImageCaptchaDTO dto : dtos) {
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
    public ImageCaptchaBO toBO() {
        return ImageCaptchaBO.builder()
                .captchaCacheName(this.getCaptchaCacheName())
                .imageData(this.getImageData())
                .build();
    }
}
