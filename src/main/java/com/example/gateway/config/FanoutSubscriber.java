package com.example.gateway.config;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by hrhrh on 2020/5/8 17:37
 */
@Component
public class FanoutSubscriber {

    @RabbitListener(queues = "product1.queue")
    public void product1(String data, Channel channel, Message message) throws IOException {
        System.out.println("进来了消息1" + data);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = "product2.queue")
    public void product2(String data, Channel channel, Message message) throws IOException {
        System.out.println("进来了消息2" + data);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
