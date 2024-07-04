package cn.fyy.message.service;

import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.common.bean.dto.ResultMessage;
import cn.fyy.message.bean.bo.ImageCaptchaBO;
import cn.fyy.message.bean.dto.MailCaptchaDTO;

/**
 * 验证码 Service
 *
 * @author fuyy
 */
public interface CaptchaService {

    /**
     * 获取图形验证码
     *
     * @return 验证码内容
     * @throws BusinessException 错误
     */
    ImageCaptchaBO getImageCaptcha() throws BusinessException;

    /**
     * 发送邮箱验证码
     *
     * @param mail                  邮箱
     * @param imageCaptcha          图形验证码
     * @param imageCaptchaCacheName 图形验证码缓存名称
     * @return 验证码内容
     * @throws BusinessException 错误
     */
    ResultMessage<MailCaptchaDTO> getMailCaptcha(String mail, String imageCaptcha, String imageCaptchaCacheName) throws BusinessException;
}
