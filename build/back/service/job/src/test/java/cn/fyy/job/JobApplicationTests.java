package cn.fyy.job;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * 测试类
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = JobApplication.class)
class JobApplicationTests {

    @Test
    void contextLoads() {
    }

}
