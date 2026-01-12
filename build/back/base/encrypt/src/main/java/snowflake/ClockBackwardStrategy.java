package snowflake;

/**
 * 添加时钟回拨处理策略枚举
 *
 * @author fyy
 */
public enum ClockBackwardStrategy {
    /**
     * 抛出异常
     */
    EXCEPTION,
    /**
     * 等待
     */
    WAIT,
    /**
     * 忽略（不推荐）
     */
    IGNORE
}
