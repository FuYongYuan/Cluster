package cn.fyy.database.util.snowflake;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import snowflake.ClockBackwardStrategy;

/**
 * 雪花ID生成器属性
 *
 * @author fyy
 */
@Data
@Component
@ConfigurationProperties(prefix = "snowflake-id")
public class SnowflakeIdProperties {

    /**
     * 工作实例ID
     */
    private Long workerId;

    /**
     * 数据中心ID
     */
    private Long dataCenterId;

    /**
     * 时间起始时间 默认2026-01-01 00:00:00.000
     */
    private Long epoch = 1767196800000L;

    /**
     * 时间倒退策略 默认 WAIT 等待
     */
    private ClockBackwardStrategy clockBackwardStrategy = ClockBackwardStrategy.WAIT;
}
