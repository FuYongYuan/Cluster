package cn.fyy.database.util.snowflake;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.Getter;
import org.springframework.stereotype.Component;
import snowflake.SnowflakeIdGenerator;

/**
 * ID 生成器工具类
 *
 * @author fyy
 */
@Component
public class SnowflakeIdUtil {
    /**
     * 雪花算法配置
     */
    @Resource
    private SnowflakeIdProperties snowflakeIdProperties;

    /**
     * 雪花算法生成器
     */
    @Getter
    private SnowflakeIdGenerator generator;

    /**
     * 确保在依赖注入完成后初始化
     */
    @PostConstruct
    public void init() {
        if (
                snowflakeIdProperties != null &&
                        snowflakeIdProperties.getWorkerId() != null &&
                        snowflakeIdProperties.getDataCenterId() != null
        ) {
            this.generator = new SnowflakeIdGenerator(
                    snowflakeIdProperties.getWorkerId(),
                    snowflakeIdProperties.getDataCenterId(),
                    snowflakeIdProperties.getEpoch(),
                    snowflakeIdProperties.getClockBackwardStrategy()
            );
        }
    }
}
