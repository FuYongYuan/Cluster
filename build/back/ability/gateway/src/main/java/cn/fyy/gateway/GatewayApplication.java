package cn.fyy.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 启动类
 *
 * @author fuyy
 */
//初始化目录
@SpringBootApplication(
        scanBasePackages = {
                // 本模块
                "cn.fyy.gateway",
                // redis模块
                "cn.fyy.redis",
                // jwt模块
                "cn.fyy.jwt",
        }
)
// 启用 gateway 网关，nacos 注册中心、配置中心
@EnableDiscoveryClient
// 动态加载配置文件
@RefreshScope
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
