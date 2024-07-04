package cn.fyy.message.bean.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 图形验证码
 *
 * @author fuyy
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ImageCaptchaBO implements Serializable {
    /**
     * 缓存名称
     */
    private String captchaCacheName;

    /**
     * 图片数据
     */
    private String imageData;


}
