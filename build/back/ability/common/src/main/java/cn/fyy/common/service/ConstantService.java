package cn.fyy.common.service;

/**
 * 静态方法类
 *
 * @author fuyy
 */
public class ConstantService {

    private ConstantService() {
        throw new IllegalStateException("ConstantService class");
    }

    //------------------------------------------------------------------------------------------------------------------日志输出

    /**
     * 日志信息
     *
     * @param e 错误内容
     * @return 日志 -> 类:***   方法:***   行:***  错误:***
     */
    public static String logMessage(Exception e) {
        return "类:" + e.getStackTrace()[0].getClassName() +
                "   方法:" + e.getStackTrace()[0].getMethodName() +
                "   行:" + e.getStackTrace()[0].getLineNumber() +
                "   错误:" + e.getMessage();
    }


}
