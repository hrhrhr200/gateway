package com.example.gateway;

import com.baitao.common.Constants;
import com.example.gateway.exception.ResponseEnum;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by hrhrh on 2020/4/29 15:36
 */
@RestController
public class TestController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/test/a")
    public void test1() {
        TokenSignKey tokenSignKey = null;
        ResponseEnum.LICENCE_NOT_FOUND.assertNotNull(tokenSignKey);
    }

    @GetMapping("/test/b")
    public void test2() {
        rabbitTemplate.convertAndSend(Constants.MQ.BAITAO_EXCHANGE, "product3.queue", "广播消息");
    }
}
