package com.example.gateway.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hrhrh on 2020/6/6 10:45
 */
public class ThreadDemo4 {

    Lock lock = new ReentrantLock();

    void m1() {

        try {
            lock.lock();
            for(int i =0;i<3;i++) {
                TimeUnit.SECONDS.sleep(i);
                System.out.println(i);
            }
        }catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
    }

    void m2() {
        boolean locked = false;

        try {
            locked = lock.tryLock(1, TimeUnit.SECONDS);
            System.out.println("m2" + locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if(locked) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ThreadDemo4 td = new ThreadDemo4();
        new Thread(td::m1).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(td::m2).start();
    }

}
