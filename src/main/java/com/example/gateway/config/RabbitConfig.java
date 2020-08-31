package com.example.gateway.config;

import com.baitao.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

/**
 * 消息队列配置
 */
@Configuration
@Slf4j
public class RabbitConfig {

    @Bean
    public MessageConverter messageConverter() {
        Jackson2JsonMessageConverter jsonMessageConverter = new Jackson2JsonMessageConverter();
        return jsonMessageConverter;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        template.setConfirmCallback(confirmCallback());
        template.setMandatory(true);
        template.setReturnCallback(returnCallback());
        return template;
    }

    @Bean
    public RabbitTemplate.ConfirmCallback confirmCallback() {
        return new ConfirmCallBackListener();
    }

    @Bean
    public RabbitTemplate.ReturnCallback returnCallback() {
        return new ConfirmCallBackListener();
    }

    @Bean
    public MappingJackson2MessageConverter jackson2Converter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        return converter;
    }

    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }

    @Bean
    public FanoutExchange exchange() {
        // 指定交换机名称
        return new FanoutExchange("test_fanout");
    }

    public TopicExchange topicExchange() {
        return new TopicExchange(Constants.MQ.BAITAO_EXCHANGE);
    }

    @Bean
    public Queue product1Queue() {
        return new Queue("product1.queue",true);
    }

    @Bean
    public Queue product2Queue() {
        return new Queue("product2.queue", true);
    }

    @Bean
    public Binding product1QueueBinding() {
        return BindingBuilder.bind(product1Queue()).to(topicExchange()).with("product1.queue");
    }

    @Bean
    public Binding product2QueueBinding() {
        return BindingBuilder.bind(product2Queue()).to(topicExchange()).with("product2.queue");

    }
}
