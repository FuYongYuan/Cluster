package cn.fyy.rocketmq.service.impl;

import cn.fyy.common.bean.bo.BusinessException;
import cn.fyy.rocketmq.bean.ao.RocketMQEndpoint;
import cn.fyy.rocketmq.service.MessageProducerService;
import jakarta.annotation.Resource;
import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息生产者
 *
 * @author fyy
 */
@Service
public class MessageProducerServiceImpl implements MessageProducerService {

    /**
     * 自动注入配置文件中绑定的通道
     */
    @Resource
    private StreamBridge streamBridge;

    /**
     * 消息发送
     *
     * @param topic          主题
     * @param tag            标签
     * @param messageContent 消息内容
     */
    @Override
    public boolean sendMessage(RocketMQEndpoint topic, String tag, String messageContent) throws BusinessException {
        Map<String, Object> headers = new HashMap<>();
        headers.put(MessageConst.PROPERTY_TAGS, tag);
        Message<String> msg = new GenericMessage<>(messageContent, headers);
        return streamBridge.send(topic.getEndpoint(), msg);
    }
}