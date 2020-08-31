package com.example.gateway.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hrhrh on 2020/6/6 10:45
 */
public class ThreadDemo3 {

    AtomicInteger count = new AtomicInteger(0);

     void m() {
        for(int i = 0; i< 10000 ;i++) {
            //System.out.println(Thread.currentThread().getName());
            count.incrementAndGet();
        }
    }

      public static void main(String[] args) {
        ThreadDemo3 td = new ThreadDemo3();

        List<Thread> threadList = new ArrayList<>();
        CountDownLatch cd = new CountDownLatch(10);
        for(int i = 0;i<10;i++) {
            threadList.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    td.m();
                    cd.countDown();
                }
            }, "thread-" + i));
        }

        threadList.forEach(o -> o.start());
        //td.m();
        /*threadList.forEach(o -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });*/
        try {
            cd.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(td.count);
    }

}
