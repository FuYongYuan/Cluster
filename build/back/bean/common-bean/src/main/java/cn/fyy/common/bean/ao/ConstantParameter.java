package cn.fyy.common.bean.ao;

/**
 * 静态参数类
 *
 * @author fuyy
 */
public class ConstantParameter {
    //------------------------------------------------------------------------------------------------------------------系统参数

    /**
     * 登陆用户JWT信息
     */
    public static final String MANAGER_JWT_INFO_KEY = "JWT-Manager-Id-";

    /**
     * 登陆用户验证码
     */
    public static final String IMAGE_CAPTCHA_KEY = "ImageCaptcha-";

    /**
     * 登陆用户验证码
     */
    public static final String MAIL_CAPTCHA_KEY = "MailCaptcha-";

    /**
     * 验证码过期时间 秒
     */
    public static final Long CAPTCHA_EXPIRATION = 600L;

    /**
     * JWT中管理员ID取值名称
     */
    public static final String JWT_MANAGER_ID = "managerId";

    /**
     * JWT中管理员名称取值名称
     */
    public static final String JWT_MANAGER_NAME = "managerName";

    /**
     * JWT中管理员用户名称取值名称
     */
    public static final String JWT_USERNAME = "username";

    /**
     * JWT中管理员密码取值名称
     */
    public static final String JWT_PASSWORD = "password";

    /**
     * JWT中管理员权限取值名称
     */
    public static final String JWT_AUTHORITIES = "authorities";

    /**
     * 登录请求错误尝试次数上限
     */
    public static final int ATTEMPT_LOGIN_FREQUENCY = 3;

    /**
     * JWT刷新剩余时间
     */
    public static final long JWT_EXPIRATION_REFRESH = 300000L;

    /**
     * 系统超级管理员ID
     */
    public static final Long SYSTEM_ADMIN_MANAGER_ID = 1L;

    /**
     * 系统普通管理员权限ID
     */
    public static final Long SYSTEM_MANAGER_ROLE_ID = 2L;

    /**
     * 系统管理员名称
     */
    public static final String SYSTEM_ADMIN_MANAGER_NAME = "系统默认超级管理员";
    //------------------------------------------------------------------------------------------------------------------文件目录
    /**
     * download目录名称
     */
    public static final String DOWNLOAD_PATH_NAME = "download";

    //------------------------------------------------------------------------------------------------------------------参数表值
    /**
     * 最大创建次数
     */
    public static final String CREATE_PROJECT_GROUP_MAX_NUMBER = "CreateProjectGroupMaxNumber";

}
