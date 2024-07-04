package cn.fyy.member;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * 测试类
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MemberApplication.class)
class MemberApplicationTests {

    @Test
    void contextLoads() {
    }

}
