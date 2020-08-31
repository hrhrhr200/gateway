package com.example.gateway.demo3;

/**
 * Created by hrhrh on 2020/7/18 11:27
 */
public class Father {

    public Father() {
        System.out.println(this.getClass());
        sayhi();
    }

    public void sayhi() {
        System.out.println("father say Hi");
    }
}
