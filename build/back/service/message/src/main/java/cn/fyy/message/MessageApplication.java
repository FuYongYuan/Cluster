package cn.fyy.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 消息模块
 *
 * @author fuyy
 */
@SpringBootApplication(
        scanBasePackages = {
                // 本模块
                "cn.fyy.message",
                // 公共模块
                "cn.fyy.common",
                // jwt模块
                "cn.fyy.jwt",
                // 数据模块
                "cn.fyy.database",
                // redis模块
                "cn.fyy.redis",
                // mail模块
                "cn.fyy.mail",
        }
)
// 启用 nacos 注册中心、配置中心
@EnableDiscoveryClient
// 动态加载配置文件
@RefreshScope
public class MessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageApplication.class, args);
    }

}
