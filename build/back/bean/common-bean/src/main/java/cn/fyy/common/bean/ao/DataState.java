package cn.fyy.common.bean.ao;

import lombok.Getter;

/**
 * 数据状态枚举
 *
 * @author fuyy
 */
@Getter
public enum DataState {
    NORMAL((byte) 0, "正常"),
    FREEZE((byte) 1, "冻结"),
    DELETE((byte) 99, "删除");

    /**
     * 代码
     */
    private final byte code;
    /**
     * 描述
     */
    private final String description;

    /**
     * 构造函数
     *
     * @param code        代码
     * @param description 描述
     */
    DataState(byte code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据代码获取数据状态
     *
     * @param code 代码
     * @return 数据状态
     */
    public static DataState fromCode(byte code) {
        for (DataState state : DataState.values()) {
            if (state.getCode() == code) {
                return state;
            }
        }
        throw new IllegalArgumentException("未知的数据状态代码: " + code);
    }

}
