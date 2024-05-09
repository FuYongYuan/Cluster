package cn.fyy.common.config.health;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * 健康检查配置
 *
 * @author fuyy
 */
@Component
public class HealthCheckConfig implements HealthIndicator {
    /**
     * 获取端口号
     */
    @Value("${server.port}")
    private String port;

    @Override
    public Health health() {
        // 添加自定义健康检查逻辑
        if (port != null && !port.isEmpty()) {
            return Health.up().withDetail("message", "正常运行").build();
        } else {
            return Health.down().withDetail("message", "错误运行").build();
        }
    }
}
