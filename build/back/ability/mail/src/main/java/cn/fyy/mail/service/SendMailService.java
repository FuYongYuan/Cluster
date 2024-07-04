package cn.fyy.mail.service;

/**
 * 发送邮件 Service
 *
 * @author fuyy
 */
public interface SendMailService {

    /**
     * 发送邮件
     *
     * @param theme   邮件主题
     * @param content 邮件内容
     * @param to      收信邮箱
     * @return String
     */
    boolean sendMail(String theme, String content, String... to);

    /**
     * 发送邮件
     *
     * @param theme   邮件主题
     * @param content 邮件内容
     * @param to      收信邮箱
     * @param cc      抄送邮箱
     * @return String
     */
    boolean sendMail(String theme, String content, String[] to, String[] cc);

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
    boolean sendMail(String theme, String content, String[] to, String[] cc, String[] bcc);

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
    boolean sendMail(String theme, String content, String[] to, String[] cc, String[] bcc, String filePath);
}
