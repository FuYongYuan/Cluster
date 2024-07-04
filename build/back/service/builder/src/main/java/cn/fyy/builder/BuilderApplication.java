package cn.fyy.builder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 启动类
 *
 * @author fuyy
 */
@SpringBootApplication(
        scanBasePackages = {
                // 本模块
                "cn.fyy.builder",
                // 公共模块
                "cn.fyy.common",
                // jwt模块
                "cn.fyy.jwt",
                // 数据模块
                "cn.fyy.database",
                // redis模块
                "cn.fyy.redis",
        }
)
// 启用 nacos 注册中心、配置中心
@EnableDiscoveryClient
// 动态加载配置文件
@RefreshScope
// 启用feign
@EnableFeignClients
public class BuilderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuilderApplication.class, args);
    }

}
