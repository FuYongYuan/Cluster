package cn.fyy.rocketmq.service;

import cn.fyy.common.bean.ao.RocketMQEndpoint;

/**
 * 消息生产者
 *
 * @author fyy
 */
public interface MessageProducerService {

    /**
     * 消息发送
     *
     * @param topic          主题
     * @param tag            标签
     * @param messageContent 消息内容
     */
    boolean sendMessage(RocketMQEndpoint topic, String tag, String messageContent);
}
