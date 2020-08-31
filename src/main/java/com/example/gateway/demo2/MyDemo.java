package com.example.gateway.demo2;

/**
 * Created by hrhrh on 2020/6/30 20:49
 */
public class MyDemo {

    public static void main(String[] args) {
        System.out.println(T.count);
    }

}


class T {
    public static T t = new T();
    public static int count = 2;



    private T() {
        count++;
    }
}