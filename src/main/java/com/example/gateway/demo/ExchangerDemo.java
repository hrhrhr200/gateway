package com.example.gateway.demo;

import java.util.concurrent.Exchanger;

/**
 * Created by hrhrh on 2020/6/6 17:04
 */
public class ExchangerDemo {

    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(()-> {
            String s = "T1";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "" + s);
        }, "t1").start();

        new Thread(()-> {
            String s = "T2";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "" + s);
        }, "t2").start();
    }
}
