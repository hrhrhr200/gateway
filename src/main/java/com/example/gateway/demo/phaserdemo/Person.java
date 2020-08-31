package com.example.gateway.demo.phaserdemo;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by hrhrh on 2020/6/6 16:14
 */
public class Person implements Runnable {

    Random r = new Random();
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public void arrive() {
        try {
            TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%s 到达现场! \n", name);
    }

    public void eat() {
        try {
            TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%s 吃完! \n", name);
    }

    public void leave() {
        try {
            TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%s 到达现场! \n", name);
    }

    @Override
    public void run() {

    }
}
