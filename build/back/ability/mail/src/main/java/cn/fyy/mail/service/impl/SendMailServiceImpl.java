package cn.fyy.mail.service.impl;

import cn.fyy.mail.config.exception.MailException;
import cn.fyy.mail.service.SendMailService;
import jakarta.annotation.Resource;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 发送邮件 Service
 *
 * @author fuyy
 */
@Slf4j
@Component
public class SendMailServiceImpl implements SendMailService {

    /**
     * Spring Boot 提供了一个发送邮件的简单抽象，使用的是下面这个接口，这里直接注入即可使用
     */
    @Resource
    private JavaMailSender javaMailSender;

    /**
     * 邮件配置
     */
    @Resource
    private MailProperties mailProperties;

    /**
     * 发送邮件
     *
     * @param theme   邮件主题
     * @param content 邮件内容
     * @param to      收信邮箱
     * @return String
     */
    @Override
    public boolean sendMail(String theme, String content, String... to) {
        return this.sendMail(theme, content, to, null, null, null);
    }

    /**
     * 发送邮件
     *
     * @param theme   邮件主题
     * @param content 邮件内容
     * @param to      收信邮箱
     * @param cc      抄送邮箱
     * @return String
     */
    @Override
    public boolean sendMail(String theme, String content, String[] to, String[] cc) {
        return this.sendMail(theme, content, to, cc, null, null);
    }

    /**
     * 发送邮件
     *
     * @param theme   邮件主题
     * @param content 邮件内容
     * @param to      收信邮箱
     * @param cc      抄送邮箱
     * @param bcc     密送邮箱
     * @return String
     */
    @Override
    public boolean sendMail(String theme, String content, String[] to, String[] cc, String[] bcc) {
        return this.sendMail(theme, content, to, cc, bcc, null);
    }

    /**
     * 发送邮件
     *
     * @param theme    邮件主题
     * @param content  邮件内容
     * @param to       收信邮箱
     * @param cc       抄送邮箱
     * @param bcc      密送邮箱
     * @param filePath 附件地址
     * @return String家庭经济管理系统
     */
    @Override
    public boolean sendMail(String theme, String content, String[] to, String[] cc, String[] bcc, String filePath) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            // 邮件发送人
            mimeMessageHelper.setFrom(mailProperties.getUsername());
            // 邮件接收人
            mimeMessageHelper.setTo(to);
            // 邮件抄送（校验 cc 是否为 null）
            if (cc != null && cc.length > 0) {
                mimeMessageHelper.setCc(cc);
            }
            // 邮件密送（校验 bcc 是否为 null）
            if (bcc != null && bcc.length > 0) {
                mimeMessageHelper.setBcc(bcc);
            }
            // 邮件主题
            mimeMessageHelper.setSubject(theme);
            // 邮件内容
            mimeMessageHelper.setText(content, true);
            // 邮件附件（校验 filePath 是否为 null）
            if (filePath != null && !filePath.isEmpty()) {
                FileSystemResource file = new FileSystemResource(new File(filePath));
                if (file.exists()) {
                    String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
                    mimeMessageHelper.addAttachment(fileName, file);
                }
            }
            // 发送邮件
            javaMailSender.send(mimeMessage);
            return true;
        } catch (Exception e) {
            throw new MailException("诊断：发送邮件错误！", e);
        }
    }


}
