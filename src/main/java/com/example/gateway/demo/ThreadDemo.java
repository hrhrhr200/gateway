package com.example.gateway.demo;

import java.util.concurrent.TimeUnit;

/**
 * Created by hrhrh on 2020/6/6 10:45
 */
public class ThreadDemo {

    String name;
    Double balance = 0.0;

    public synchronized void set(String name, Double balance) {
        this.name = name;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.balance = balance;
    }

    public double getBalance(String name){
        return this.balance;
    }

    public static void main(String[] args) {
        ThreadDemo td = new ThreadDemo();
        new Thread(() -> td.set("aaa", 100.0)).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(td.getBalance("aaa"));

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(td.getBalance("aaa"));
    }
}
