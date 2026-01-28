package cn.fyy.common.bean.ao;

/**
 * 静态参数类
 *
 * @author fuyy
 */
public class ConstantParameter {
    //------------------------------------------------------------------------------------------------------------------系统参数

    /**
     * 登陆用户 JWT 信息
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
     * JWT 中管理员 ID 取值名称
     */
    public static final String JWT_MANAGER_ID = "managerId";

    /**
     * JWT 中管理员名称取值名称
     */
    public static final String JWT_MANAGER_NAME = "managerName";

    /**
     * JWT 中管理员用户名称取值名称
     */
    public static final String JWT_USERNAME = "username";

    /**
     * JWT 中管理员密码取值名称
     */
    public static final String JWT_PASSWORD = "password";

    /**
     * JWT 中管理员权限取值名称
     */
    public static final String JWT_AUTHORITIES = "authorities";

    /**
     * 登录请求错误尝试次数上限
     */
    public static final int ATTEMPT_LOGIN_FREQUENCY = 3;

    /**
     * JWT 刷新剩余时间
     */
    public static final long JWT_EXPIRATION_REFRESH = 300000L;

    /**
     * 系统超级管理员 ID
     */
    public static final Long SYSTEM_ADMIN_MANAGER_ID = 6508370886681600L;

    /**
     * 系统普通管理员权限 ID
     */
    public static final Long SYSTEM_MANAGER_ROLE_ID = 6514166638888960L;

    /**
     * 系统管理员名称
     */
    public static final String SYSTEM_ADMIN_MANAGER_NAME = "系统默认超级管理员";
    //------------------------------------------------------------------------------------------------------------------文件目录
    /**
     * download 目录名称
     */
    public static final String DOWNLOAD_PATH_NAME = "download";

    /**
     * 上传用户数据目录名称
     */
    public static final String UPLOAD_USER_DATA_PATH_NAME = "user-data";

    /**
     * 用户头像业务类型
     */
    public static final String BUSINESS_TYPE_USER_HEAD_IMAGE = "head-image";

    /**
     * 文件读取过期时间 秒
     */
    public static final Long FILE_READ_EXPIRATION_SECOND = 60L;

    /**
     * 文件读取过期时间 分钟
     */
    public static final Long FILE_READ_EXPIRATION_MINUTE = FILE_READ_EXPIRATION_SECOND * 60L;


    //------------------------------------------------------------------------------------------------------------------参数表值
    /**
     * 最大创建次数
     */
    public static final String CREATE_PROJECT_GROUP_MAX_NUMBER = "CreateProjectGroupMaxNumber";

}
