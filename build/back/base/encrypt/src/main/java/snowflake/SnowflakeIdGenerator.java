package snowflake;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 雪花算法 ID 生成器 - 线程安全、高性能
 * 64位 ID: 1位(符号) + 41位(时间戳) + 10位(实例 ID) + 4位(数据中心 ID) + 8位(序列号)
 *
 * @author fyy
 */
public class SnowflakeIdGenerator {
    //------------------------------------------------------------------------------------------------------------------实例变量
    /**
     * 工作实例 ID
     */
    private final long workerId;

    /**
     * 数据中心 ID
     */
    private final long dataCenterId;

    /**
     * 起始时间戳
     */
    private final long epoch;

    /**
     * 上次时间戳
     */
    private final AtomicLong lastTimestamp = new AtomicLong(-1L);

    /**
     * 序列号-使用原子操作和更细粒度的锁
     */
    private final AtomicLong sequence = new AtomicLong(0L);

    /**
     * 时钟回拨处理策略
     */
    private final ClockBackwardStrategy clockBackwardStrategy;

    //------------------------------------------------------------------------------------------------------------------构造方法

    /**
     * 构造函数
     *
     * @param workerId     工作实例 ID (0~1023)
     * @param dataCenterId 数据中心 ID (0~15)
     */
    public SnowflakeIdGenerator(long workerId, long dataCenterId, long epoch) {
        this(workerId, dataCenterId, epoch, ClockBackwardStrategy.WAIT);
    }

    /**
     * 构造函数
     *
     * @param workerId     工作实例 ID (0~1023)
     * @param dataCenterId 数据中心 ID (0~15)
     * @param epoch        起始时间戳
     * @param strategy     时钟回拨处理策略
     */
    public SnowflakeIdGenerator(long workerId, long dataCenterId, long epoch, ClockBackwardStrategy strategy) {
        if (workerId > SnowflakeIdConstant.MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("工作实例 ID 必须介于 0 至 %d", SnowflakeIdConstant.MAX_WORKER_ID));
        }
        if (dataCenterId > SnowflakeIdConstant.MAX_DATA_CENTER_ID || dataCenterId < 0) {
            throw new IllegalArgumentException(
                    String.format("数据中心 ID 必须介于 0 至 %d", SnowflakeIdConstant.MAX_DATA_CENTER_ID));
        }
        if (epoch > System.currentTimeMillis()) {
            throw new IllegalArgumentException(
                    "起始时间戳 必须小于 当前时间");
        }

        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
        this.epoch = epoch;
        this.clockBackwardStrategy = strategy != null ? strategy : ClockBackwardStrategy.WAIT;

    }

    //------------------------------------------------------------------------------------------------------------------核心方法

    /**
     * 生成下一个 ID
     *
     * @return 雪花算法生成的唯一 ID
     */
    public long nextId() {
        long timestamp = timeGen();
        long lastTs = lastTimestamp.get();

        // 处理时钟回拨
        if (timestamp < lastTs) {
            long offset = lastTs - timestamp;
            switch (clockBackwardStrategy) {
                case EXCEPTION:
                    if (offset > 5000) {
                        throw new RuntimeException(
                                String.format("检测到时钟回拨，拒绝生成 ID %d 毫秒", offset));
                    }
                    break;
                case WAIT:
                    if (offset > 5000) {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            throw new RuntimeException("线程被中断", e);
                        }
                    } else {
                        try {
                            Thread.sleep(offset);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            throw new RuntimeException("线程被中断", e);
                        }
                    }
                    timestamp = timeGen();
                    break;
                case IGNORE:
                    timestamp = lastTs;
                    break;
                default:
                    throw new IllegalArgumentException("不支持的时钟回拨策略");
            }
        }

        // 检查是否需要更新时间戳
        if (lastTimestamp.compareAndSet(lastTs, timestamp)) {
            // 同一毫秒内生成多个 ID
            if (lastTs == timestamp) {
                long currentSequence = sequence.incrementAndGet() & SnowflakeIdConstant.SEQUENCE_MASK;
                if (currentSequence == 0) {
                    // 序列号已用完，等待下一毫秒
                    timestamp = tilNextMillis(lastTs);
                    lastTimestamp.set(timestamp);
                    sequence.set(0L);
                }
            } else {
                // 不同毫秒，序列号重置
                sequence.set(0L);
            }

            return ((timestamp - epoch) << SnowflakeIdConstant.TIMESTAMP_LEFT_SHIFT) |
                    (dataCenterId << SnowflakeIdConstant.DATA_CENTER_ID_SHIFT) |
                    (workerId << SnowflakeIdConstant.WORKER_ID_SHIFT) |
                    sequence.get();
        } else {
            // CAS 失败，递归调用
            return nextId();
        }
    }

    /**
     * 等待下一毫秒
     *
     * @param lastTimestamp 上次时间戳
     * @return 下一毫秒时间戳
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 获取当前时间戳
     *
     * @return 当前时间戳
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 验证配置的有效性
     *
     * @return true参数有效，false参数无效
     */
    public boolean isValidConfig() {
        return workerId >= 0 && workerId <= SnowflakeIdConstant.MAX_WORKER_ID &&
                dataCenterId >= 0 && dataCenterId <= SnowflakeIdConstant.MAX_DATA_CENTER_ID &&
                epoch < System.currentTimeMillis();
    }
}
