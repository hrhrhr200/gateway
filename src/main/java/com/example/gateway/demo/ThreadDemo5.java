package com.example.gateway.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by hrhrh on 2020/6/6 16:47
 */
public class ThreadDemo5 {

    static Lock lock = new ReentrantLock();
    static int value = 1;

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    public static void read(Lock lock) {

        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("read over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock, int v) {
        lock.lock();
        try {
            Thread.sleep(1000);
            value = v;
            System.out.println("write over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Runnable a = new Runnable() {
            @Override
            public void run() {
                read(readLock);
            }
        };

        Runnable b = new Runnable() {
            @Override
            public void run() {
                write(writeLock, 2);
            }
        };

        for(int i=0;i<18;i++){
            new Thread(a).start();
        }
        for(int i=0;i<2;i++){
            new Thread(b).start();
        }
    }
}
