package cn.fyy.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;

/**
 * 数据模块
 *
 * @author fyy
 */
@SpringBootApplication
// 扫描包
@ComponentScan({
        // 本模块
        "cn.fyy.data",
        // 公共模块
        "cn.fyy.common",
        // jwt 模块
        "cn.fyy.jwt",
        // redis 模块
        "cn.fyy.redis",
        // RustFS 模块
        "cn.fyy.rustfs",
})
// 启用 nacos 注册中心、配置中心
@EnableDiscoveryClient
// 动态加载配置文件
@RefreshScope
public class DataApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataApplication.class, args);
    }

}
