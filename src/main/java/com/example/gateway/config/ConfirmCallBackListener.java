package com.example.gateway.config;

import com.rabbitmq.client.ConfirmListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by hrhrh on 2020/5/11 15:17
 */
@Slf4j
public class ConfirmCallBackListener implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("confirm--->[correlationData={},ack={},cause={}]",
                correlationData,
                ack,
                cause);
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("失败后回调return--->[message={},replyCode={},replyText={},exchange={},routingKey={}]",
                new String(message.getBody()),
                replyCode,
                replyText,
                exchange,
                routingKey);
    }
}
