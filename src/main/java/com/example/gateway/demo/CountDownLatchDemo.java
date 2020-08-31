package com.example.gateway.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Created by hrhrh on 2020/6/1 14:44
 */
public class CountDownLatchDemo {
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch;

        for(int i = 0; i < 2; i++) {
            latch = new CountDownLatch(2);
            Worker w1 = new Worker("张三", 2000, latch);
            Worker w2 = new Worker("李四", 3000, latch);
            w1.start();
            w2.start();

            long startTime = System.currentTimeMillis();
            latch.await();
            System.out.println("bug全部解决，领导可以给客户交差了，任务总耗时："+ (System.currentTimeMillis() - startTime));
        }


    }

    static class Worker extends Thread {
        String name;
        int workTime;
        CountDownLatch latch;

        public Worker(String name, int workTime, CountDownLatch latch) {
            this.name = name;
            this.workTime = workTime;
            this.latch = latch;
        }

        @Override
        public void run() {
            //System.out.println(name+"开始修复bug，当前时间："+sdf.format(new Date()));
            doWork();
            System.out.println(name+"修复bug，当前时间："+sdf.format(new Date()));
            latch.countDown();
        }

        private void doWork() {
            try {
                //模拟工作耗时
                Thread.sleep(workTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
