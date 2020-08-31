package com.example.gateway.demo;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by hrhrh on 2020/6/12 15:48
 */
public class ThreadDemo7 {

    static Queue<String> tickets = new ConcurrentLinkedQueue<>();

    static {
        for(int i = 0; i< 1000; i++) {
            tickets.add("票编号" + i);
        }
    }

    public static void main(String[] args) {
        for(int i = 0;i<10;i++) {
            new Thread(()->{
                while (true) {
                    String s = tickets.poll();
                    if(null == s) {
                        break;
                    }
                    System.out.println("销售了--" + s);
                }
            }).start();
        }
    }
}
