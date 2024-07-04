package cn.fyy.message.restcontroller;

import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.message.bean.dto.MailCaptchaDTO;
import cn.fyy.message.service.CaptchaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 邮件发送 RestController
 *
 * @author fuyy
 */
@Tag(name = "MailRestController", description = "邮件 RestController")
@RestController
@RequestMapping(value = "/mail", produces = MediaType.APPLICATION_JSON_VALUE)
public class MailRestController {
    /**
     * 验证码Service
     */
    @Resource
    private CaptchaService captchaServiceImpl;

    //------------------------------------------------------------------------------------------------------------------自定义方法

    /**
     * 发送邮箱验证码
     *
     * @param mail 邮箱
     * @return 邮箱对象
     * @throws BusinessException 业务错误
     */
    @Operation(
            summary = "发送邮箱验证码", description = "是否成功",
            parameters = {
                    @Parameter(name = "mail", description = "邮箱", required = true),
                    @Parameter(name = "imageCaptcha", description = "验证码", required = true),
                    @Parameter(name = "imageCaptchaCacheName", description = "验证码缓存名称", required = true)
            }
    )
    @GetMapping(value = "/send/captcha/{mail}/{imageCaptcha}/{imageCaptchaCacheName}")
    public ResultMessage<MailCaptchaDTO> sendCaptcha(
            @PathVariable("mail") String mail,
            @PathVariable("imageCaptcha") String imageCaptcha,
            @PathVariable("imageCaptchaCacheName") String imageCaptchaCacheName
    ) throws BusinessException {
        return captchaServiceImpl.getMailCaptcha(mail, imageCaptcha, imageCaptchaCacheName);
    }
}
