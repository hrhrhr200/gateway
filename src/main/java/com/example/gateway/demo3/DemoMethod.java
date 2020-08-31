package com.example.gateway.demo3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hrhrh on 2020/7/17 17:06
 */
public class DemoMethod {

    public static void main(String[] args) {
        /*SSSForSet<String> s = new SSSForSet<>(new HashSet<>());
        System.out.println(s.getAddcouNT());
        List<String> stringList = Arrays.asList("aaa", "bbb", "ccc");

        s.addAll(stringList);

        System.out.println(s.getAddcouNT());*/

        /*Son s  = new Son();
        s.sayhi();*/

        Object[] strings = pickTwo("a", "b", "c");
    }

    public static<T> T[] toArray(T... arg){
        return arg;
    }

    public static<T> T[] pickTwo(T a, T b, T c) {
        return toArray(a,b,c);
    }
}
