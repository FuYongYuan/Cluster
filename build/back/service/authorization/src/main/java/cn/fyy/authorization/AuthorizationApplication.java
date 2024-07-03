package cn.fyy.authorization;

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
                "cn.fyy.authorization",
                // 公共模块
                "cn.fyy.common",
                // jwt模块
                "cn.fyy.jwt",
        }
)
// 启用 nacos 注册中心、配置中心
@EnableDiscoveryClient
// 动态加载配置文件
@RefreshScope
public class AuthorizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationApplication.class, args);
    }

}
