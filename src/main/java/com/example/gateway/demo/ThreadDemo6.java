package com.example.gateway.demo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by hrhrh on 2020/6/12 14:21
 */
public class ThreadDemo6 {

    static Map<UUID, UUID> m = new ConcurrentHashMap<>();
    static  int count = 1000000;
    static UUID[] keys = new UUID[count];
    static UUID[] values = new UUID[count];
    static final int THREAD_COUNT = 100;

    static {
        for(int i = 0; i < count; i++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    static class MyThead extends Thread{
        int start;
        int gap = count/THREAD_COUNT;

        public MyThead(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            for(int i=start; i < start + gap; i++) {
                m.put(keys[i], values[i]);
            }
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Thread[] threads = new Thread[THREAD_COUNT];

        for(int i = 0; i< threads.length; i++) {
            threads[i] = new MyThead(i * (count/THREAD_COUNT));
        }

        for(Thread t : threads) {
            t.start();
        }

        for(Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);

        System.out.println(m.size());

        /*start = System.currentTimeMillis();

        for(int i=0; i< threads.length; i++) {
            threads[i] = new Thread(()-> {
                for(int j=0;j<1000000; j++) {
                    m.get(keys[j]);
                }
            });
        }

        for(Thread t : threads) {
            t.start();
        }
        for(Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        end = System.currentTimeMillis();
        System.out.println(end - start);*/

    }
}
