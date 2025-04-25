package cn.fyy.common.bean.ao;


import lombok.Getter;

/**
 * 操作结果枚举
 *
 * @author fuyy
 */
@Getter
public enum OperateResult {
    SUCCESS("操作成功！"),
    FAIL("操作失败！");

    /**
     * 操作结果信息
     */
    private final String message;

    /**
     * 构造方法
     *
     * @param message 操作结果信息
     */
    OperateResult(String message) {
        this.message = message;
    }

}
