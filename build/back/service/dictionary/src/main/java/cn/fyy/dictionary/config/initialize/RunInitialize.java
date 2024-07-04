package cn.fyy.dictionary.config.initialize;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 项目运行成功后立即执行
 *
 * @author fuyy
 */
@Slf4j
@Component
public class RunInitialize implements CommandLineRunner {

    /**
     * 项目运行成功后立即执行
     */
    @Override
    public void run(String... args) throws Exception {
        log.info("<<<<<<<<<<<<======== 初始化项目所需内容 开始 ========>>>>>>>>>>>>");


        log.info("<<<<<<<<<<<<======== 初始化项目所需内容 结束 ========>>>>>>>>>>>>");
    }

}