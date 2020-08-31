package com.example.gateway.demo;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hrhrh on 2020/6/1 15:25
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(1, new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("等裁判吹口哨...");
                    //这里停顿两秒更便于观察线程执行的先后顺序
                    Thread.sleep(2000);
                    System.out.println("裁判吹口哨->>>>>");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });  //①
        Runner runner1 = new Runner(barrier, "张三");
        Runner runner2 = new Runner(barrier, "李四");
        Runner runner3 = new Runner(barrier, "王五");

        ExecutorService service = Executors.newFixedThreadPool(3);
        service.execute(runner1);
        service.execute(runner2);
        service.execute(runner3);

        service.shutdown();
    }

    static class Runner implements Runnable {

        private CyclicBarrier barrier;
        private String name;

        public Runner(CyclicBarrier barrier, String name) {
            this.barrier = barrier;
            this.name = name;

        }

        @Override
        public void run() {
            try{
                Thread.sleep(new Random().nextInt(5000));
                System.out.println(name + ":准备OK");
                barrier.await();
                System.out.println(name +": 开跑");
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
