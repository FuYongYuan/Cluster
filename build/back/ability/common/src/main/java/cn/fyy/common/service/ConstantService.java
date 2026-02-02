package cn.fyy.common.service;

import org.springframework.beans.factory.annotation.Value;

/**
 * 静态方法类
 *
 * @author fyy
 */
public class ConstantService {
    /**
     * 当前环境
     */
    @Value("${spring.profiles.active}")
    public static String active;

    //------------------------------------------------------------------------------------------------------------------日志输出

    /**
     * 日志信息
     *
     * @param e 错误内容
     * @return 日志 -> 类:***   方法:***   行:***  错误:***
     */
    public static String logMessage(Exception e) {
        // 当环境是开发环境或者测试环境时，打印错误堆栈信息
        if ("dev".equals(active) || "test".equals(active)) {
            e.printStackTrace();
        }

        return "类:" + e.getStackTrace()[0].getClassName() +
                "   方法:" + e.getStackTrace()[0].getMethodName() +
                "   行:" + e.getStackTrace()[0].getLineNumber() +
                "   错误:" + e.getMessage();
    }


}
