package cn.fyy.message.service.impl;

import cn.fyy.common.bean.ao.ConstantParameter;
import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.mail.service.SendMailService;
import cn.fyy.message.bean.bo.ImageCaptchaBO;
import cn.fyy.message.bean.bo.MailCaptchaBO;
import cn.fyy.message.bean.dto.MailCaptchaDTO;
import cn.fyy.message.service.CaptchaService;
import cn.fyy.message.util.CaptchaUtil;
import cn.fyy.message.util.ImageCaptchaUtil;
import cn.fyy.redis.bean.ao.RedisSelect;
import cn.fyy.redis.service.RedisService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 验证码 Service
 *
 * @author fuyy
 */
@Slf4j
@Service
public class CaptchaServiceImpl implements CaptchaService {

    /**
     * Redis 业务类
     */
    @Resource
    private RedisService redisServiceImpl;

    /**
     * 发送邮件
     */
    @Resource
    private SendMailService sendMailServiceImpl;

    /**
     * 邮件配置
     */
    @Resource
    private MailProperties mailProperties;


    //------------------------------------------------------------------------------------------------------------------自定义方法

    /**
     * 获取图形验证码
     *
     * @return 验证码内容
     * @throws BusinessException 错误
     */
    @Override
    public ImageCaptchaBO getImageCaptcha() throws BusinessException {
        // 直接调用静态方法，返回验证码对象
        ImageCaptchaUtil.Validate captcha = ImageCaptchaUtil.getRandomCode();
        if (captcha != null) {
            String captchaCacheName = ConstantParameter.IMAGE_CAPTCHA_KEY + CaptchaUtil.cacheCode();
            redisServiceImpl.set(
                    RedisSelect.FOURTEEN,
                    captchaCacheName,
                    captcha.getValue(),
                    ConstantParameter.CAPTCHA_EXPIRATION
            );
            return ImageCaptchaBO.builder().captchaCacheName(captchaCacheName).imageData(captcha.getBase64Str()).build();
        } else {
            throw new BusinessException("获取验证码失败！");
        }
    }

    /**
     * 发送邮箱验证码
     *
     * @param mail                  邮箱
     * @param imageCaptcha          图形验证码
     * @param imageCaptchaCacheName 图形验证码缓存名称
     * @return 验证码内容
     * @throws BusinessException 错误
     */
    @Override
    public ResultMessage<MailCaptchaDTO> getMailCaptcha(String mail, String imageCaptcha, String imageCaptchaCacheName) throws BusinessException {
        if (mail != null) {
            // 服务器验证码
            String captchaCheck = redisServiceImpl.get(RedisSelect.FOURTEEN, imageCaptchaCacheName, String.class);
            if (StringUtils.hasText(captchaCheck)) {
                if (imageCaptcha.equalsIgnoreCase(captchaCheck)) {
                    String randomNumber = this.sendCaptcha(mail);
                    if (StringUtils.hasText(randomNumber)) {
                        String mailCaptchaCacheName = ConstantParameter.MAIL_CAPTCHA_KEY + CaptchaUtil.cacheCode();
                        redisServiceImpl.set(
                                RedisSelect.FOURTEEN,
                                mailCaptchaCacheName,
                                randomNumber,
                                ConstantParameter.CAPTCHA_EXPIRATION
                        );
                        return new ResultMessage<>(MailCaptchaDTO.toDTO(MailCaptchaBO.builder().captchaCacheName(mailCaptchaCacheName).build()));
                    } else {
                        return new ResultMessage<>(4, "发送失败！");
                    }
                } else {
                    return new ResultMessage<>(3, "图形验证码错误！");
                }
            } else {
                return new ResultMessage<>(2, "图形验证码已过期！");
            }
        } else {
            return new ResultMessage<>(1, "请填写正确的电子邮箱！");
        }
    }

    /**
     * 向邮箱发送验证码
     *
     * @param mail 接收方邮箱地址
     * @return 用户
     */
    private String sendCaptcha(String mail) throws BusinessException {
        try {

            String randomNumber = CaptchaUtil.cacheCode();

            boolean sendState = sendMailServiceImpl.sendMail(
                    mailProperties.getProperties().get("personal") + "验证码",
                    "您好：<br>" +
                            "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + mailProperties.getProperties().get("personal") + "，您的验证码是：" + randomNumber + "！"
                    ,
                    mail
            );

            if (sendState) {
                return randomNumber;
            } else {
                throw new BusinessException("发送失败");
            }
        } catch (Exception e) {
            throw new BusinessException("向电子邮箱发送验证码错误", e);
        }
    }

}
