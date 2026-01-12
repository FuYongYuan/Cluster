package snowflake;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * ID分解信息
 *
 * @author fyy
 */
@Getter
@ToString
public class SnowflakeIdInfo {
    /**
     * 实际生成时间戳
     */
    private long timestamp;

    /**
     * 生产时间
     */
    private LocalDateTime createTime;

    /**
     * 数据中心ID
     */
    private long dataCenterId;

    /**
     * 工作节点ID
     */
    private long workerId;

    /**
     * 序列号
     */
    private long sequence;


    /**
     * 解析ID信息
     *
     * @param id ID
     * @return ID信息
     */
    public static SnowflakeIdInfo parseId(long id) {
        return parseId(id, SnowflakeIdConstant.DEFAULT_EPOCH);
    }

    /**
     * 解析ID信息
     *
     * @param id    ID
     * @param epoch 起始时间戳
     * @return ID信息
     */
    public static SnowflakeIdInfo parseId(long id, long epoch) {
        if (epoch > System.currentTimeMillis()) {
            throw new IllegalArgumentException("起始时间戳不能大于当前时间");
        }

        SnowflakeIdInfo info = new SnowflakeIdInfo();

        // 提取时间戳部分
        long timestampPart = id >>> SnowflakeIdConstant.TIMESTAMP_LEFT_SHIFT;
        info.timestamp = epoch + timestampPart;

        // 使用系统默认时区转换为本地时间
        info.createTime = LocalDateTime.ofInstant(
                java.time.Instant.ofEpochMilli(info.timestamp),
                java.time.ZoneId.systemDefault()
        );

        // 提取数据中心ID
        info.dataCenterId = (id >> SnowflakeIdConstant.DATA_CENTER_ID_SHIFT) & SnowflakeIdConstant.MAX_DATA_CENTER_ID;

        // 提取工作节点ID
        info.workerId = (id >> SnowflakeIdConstant.WORKER_ID_SHIFT) & SnowflakeIdConstant.MAX_WORKER_ID;

        // 提取序列号
        info.sequence = id & SnowflakeIdConstant.SEQUENCE_MASK;

        return info;
    }

}