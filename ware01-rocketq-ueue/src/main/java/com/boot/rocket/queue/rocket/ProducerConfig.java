package com.boot.rocket.queue.rocket;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2020/6/19 0019.
 * RocketMQ 生产者配置
 */
@Configuration
public class ProducerConfig {
    private static final Logger logger = LoggerFactory.getLogger(ProducerConfig.class) ;

    @Value("${rocketmq.producer.groupName}")
    private String groupName;
    @Value("${rocketmq.producer.namesrvAddr}")
    private String namesrvAddr;
    @Value("${rocketmq.producer.maxMessageSize}")
    private Integer maxMessageSize ;
    @Value("${rocketmq.producer.sendMsgTimeout}")
    private Integer sendMsgTimeout;
    @Value("${rocketmq.producer.retryTimesWhenSendFailed}")
    private Integer retryTimesWhenSendFailed;

    @Bean
    public DefaultMQProducer getRocketMQProducer(){
        DefaultMQProducer producer = new DefaultMQProducer(groupName);
        producer.setNamesrvAddr(namesrvAddr);

        //如果需要同一个jvm中不同的producer往不同的mq集群发送消息，需要设置不同的instanceName

        // 消息最大长度 默认1024*4(4M)
        if(null != maxMessageSize){
            producer.setMaxMessageSize(maxMessageSize);
        }
        // 发送消息超时时间,默认3000
        if(null != sendMsgTimeout){
            producer.setSendMsgTimeout(sendMsgTimeout);
        }
        //如果发送消息失败，设置重试次数，默认为2次
        if(null != retryTimesWhenSendFailed){
            producer.setRetryTimesWhenSendFailed(retryTimesWhenSendFailed);
        }

        try {
            producer.start();
        } catch (MQClientException e) {
           e.printStackTrace();
        }
        return producer;
    }

}
