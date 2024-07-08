package cn.fyy.jwt.bean.ao;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 过滤url
 *
 * @author fyy
 */
@Component
public class PermitUrl {
    /**
     * 不鉴权URL
     */
    private final String[] apiUrl = {
            // API 页面
            "/swagger-resources/**",
            "/v3/**",
            "/swagger-ui/**"
    };

    /**
     * 不鉴权URL
     */
    private final String[] projectUrl = {
            // 项目图标
            "/favicon.ico",
    };

    /**
     * 不鉴权URL
     */
    private final String[] druidUrl = {
            // 忽略数据库监控
            "/druid/**",
    };

    /**
     * 不鉴权URL
     */
    private final String[] messageUrl = {
            // 图形验证码
            "/message/image/get/captcha",
            // 邮件验证码
            "/mail/send/captcha/**",
    };

    /**
     * 不鉴权URL
     */
    private final String[] authorizationUrl = {
            // 登录、注册、找回密码 数据请求方法
            "/system/login/**",
            "/system/register/**",
            "/system/recover/**",
            "/system/logout/**",
    };

    /**
     * 不鉴权URL
     */
    private final String[] memberUrl = {
            // 管理员模块需配合登录、注册、找回密码使用的越鉴权接口
            "/manager/save/return/dto/**",
            "/manager/get/account/exist/**",
            "/manager/get/mail/exist/**",
            "/manager/get/attempt/login/number/**",
            "/manager/update/login/password/**",
            "/manager/get/account/password/**",
    };

    /**
     * 不鉴权地址
     *
     * @param url 可新增自定义的url
     * @return 自定义不鉴权的url集合
     */
    public String[] getAll(String... url) {
        Set<String> permitUrl = new HashSet<>();
        if (url.length > 0) {
            Collections.addAll(permitUrl, url);
        }
        //不鉴权地址
        Collections.addAll(permitUrl, apiUrl);
        Collections.addAll(permitUrl, projectUrl);
        Collections.addAll(permitUrl, druidUrl);
        Collections.addAll(permitUrl, messageUrl);
        Collections.addAll(permitUrl, authorizationUrl);
        Collections.addAll(permitUrl, memberUrl);
        return permitUrl.toArray(new String[0]);
    }
}
