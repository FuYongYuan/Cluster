package cn.fyy.rocketmq.bean.ao;


import lombok.Getter;

/**
 * RocketMQ 端点
 *
 * @author fyy
 */
@Getter
public enum RocketMQEndpoint {
    /**
     * 默认的 Topic
     */
    TOPIC_SHARE("share"),
    /**
     * 默认的 生产者
     */
    SHARE_OUT_0("share-out-0"),
    /**
     * 默认的 消费者
     */
    SHARE_IN_0("share-in-0");

    /**
     * 端点
     */
    private final String endpoint;

    /**
     * 构造函数
     *
     * @param endpoint 端点
     */
    RocketMQEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

}
