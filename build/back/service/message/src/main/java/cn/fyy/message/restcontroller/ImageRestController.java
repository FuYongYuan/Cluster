package cn.fyy.message.restcontroller;


import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.message.bean.dto.ImageCaptchaDTO;
import cn.fyy.message.service.CaptchaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 图片 RestController
 *
 * @author fuyy
 */
@Tag(name = "ImageRestController", description = "图片 RestController")
@RestController
@RequestMapping(value = "/image", produces = MediaType.APPLICATION_JSON_VALUE)
public class ImageRestController {
    /**
     * 验证码Service
     */
    @Resource
    private CaptchaService captchaServiceImpl;

    //------------------------------------------------------------------------------------------------------------------自定义方法

    /**
     * 获取图形验证码
     */
    @Operation(
            summary = "获取图形验证码", description = "验证码Base64字符串"
    )
    @GetMapping(value = "/get/captcha")
    public ResultMessage<ImageCaptchaDTO> getImageCaptcha(
    ) throws BusinessException {
        return new ResultMessage<>(ImageCaptchaDTO.toDTO(captchaServiceImpl.getImageCaptcha()));
    }
}
