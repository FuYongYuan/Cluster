package cn.fyy.common.bean.ao;


/**
 * 操作结果枚举
 *
 * @author fuyy
 */
public class OperateResult {
    /**
     * 初始化
     */
    private OperateResult() {
        throw new IllegalStateException("初始化OperateResult.class错误");
    }

    /**
     * 操作成功
     */
    public static final String SUCCESS = "操作成功！";

    /**
     * 操作失败
     */
    public static final String FAIL = "操作失败！";
}
