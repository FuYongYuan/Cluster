package snowflake;

/**
 * 雪花 ID 生成器常量
 *
 * @author fyy
 */
public class SnowflakeIdConstant {
    //------------------------------------------------------------------------------------------------------------------常量定义
    /**
     * 实例 ID 位数
     */
    public static final long WORKER_ID_BITS = 10L;

    /**
     * 数据中心 ID 位数
     */
    public static final long DATA_CENTER_ID_BITS = 4L;

    /**
     * 序列号位数
     */
    public static final long SEQUENCE_BITS = 8L;

    /**
     * 最大实例 ID (0~1023)
     */
    public static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);

    /**
     * 最大数据中心 ID (0~15)
     */
    public static final long MAX_DATA_CENTER_ID = ~(-1L << DATA_CENTER_ID_BITS);

    /**
     * 实例 ID 左移位数
     */
    public static final long WORKER_ID_SHIFT = SEQUENCE_BITS;

    /**
     * 数据中心 ID 左移位数
     */
    public static final long DATA_CENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;

    /**
     * 时间戳左移位数
     */
    public static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATA_CENTER_ID_BITS;

    /**
     * 序列号掩码
     */
    public static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);
}
