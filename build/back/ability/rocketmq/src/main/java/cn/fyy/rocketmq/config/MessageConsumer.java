package cn.fyy.rocketmq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * 消费者
 *
 * @author fyy
 */
@Component
public class MessageConsumer {

    /**
     * 通过方法名称自动绑定到符合条件的消费者通道
     *
     * @return 消费者
     */
    @Bean
    public Consumer<Message<String>> share() {
        System.out.println("share 接受到消息");
        return msg -> {
            try {
                System.out.println(Thread.currentThread().getName() + "读取信息: " + msg);
            } catch (Exception e) {
                e.printStackTrace(); // 记录异常堆栈信息
            }
        };
    }
}