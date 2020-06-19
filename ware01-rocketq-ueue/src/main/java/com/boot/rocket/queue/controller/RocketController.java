package com.boot.rocket.queue.controller;

import com.boot.rocket.queue.service.RocketMqService;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2020/6/19 0019.
 */
@RestController
@RequestMapping("/mq")
public class RocketController {
    @Autowired
    private RocketMqService rocketMqService;

    @RequestMapping("/sendMsg")
    public SendResult sendMsg (){
        String msg = "OpenAccount Msg";
        SendResult sendResult = null;
        try {
            sendResult = rocketMqService.sendMsg(msg) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendResult ;
    }
}
