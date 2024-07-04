package cn.fyy.message.util;

import encrypt.RandomUtil;

/**
 * 验证码工具类
 *
 * @author fuyy
 */
public class CaptchaUtil {
    /**
     * 缓存的随机编码
     *
     * @return 当前时间戳+随机6位数
     */
    public static String cacheCode() {
        long thisTime = System.currentTimeMillis();
        return thisTime + RandomUtil.randomNumberByLength(6);
    }
}
