package com.example.gateway.demo2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hrhrh on 2020/7/7 15:41
 */
public class DeadLockDemo {

   private static ReentrantLock r1 = new ReentrantLock();
   private static ReentrantLock r2 = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            r1.lock();
            try {
                TimeUnit.SECONDS.sleep(1);
                r2.lock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "Thread1");

        Thread t2 = new Thread(() -> {
            r2.lock();
            try {
                TimeUnit.SECONDS.sleep(1);
                r1.lock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "Thread2");

        t1.start();
        t2.start();
    }
}
