package com.example.gateway.redisDemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Type;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by hrhrh on 2020/7/29 17:29
 */
public class RedisDelayingQueue<T> {

    static class Taskitem<T> {
        private String msgId;

        private T msg;
    }

    private Jedis jedis;

    private String queueKey;

    private Type taskType = new TypeReference<Taskitem<T>>(){

    }.getType();

    public RedisDelayingQueue(Jedis jedis, String queueKey) {
        this.jedis = jedis;
        this.queueKey = queueKey;
    }

    public void delay(T msg) {
        Taskitem<T> task = new Taskitem<>();

        task.msgId = UUID.randomUUID().toString();

        task.msg = msg;

        String s = JSON.toJSONString(task);

        jedis.zadd(queueKey, System.currentTimeMillis() + 5000 , s);

    }

    public void loop() {
        while(!Thread.interrupted()) {
            Set<String> set = jedis.zrangeByScore(queueKey, 0, System.currentTimeMillis(), 0, 1);
            if(set.isEmpty()) {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
                continue;
            }
            String s = set.iterator().next();
            if(jedis.zrem(queueKey, s) > 0) {
                Taskitem<T> o = JSON.parseObject(s, taskType);
                this.handleMsg(o.msg);
            }
        }
    }

    private void handleMsg(T msg) {
        System.out.println(msg);
    }
}
