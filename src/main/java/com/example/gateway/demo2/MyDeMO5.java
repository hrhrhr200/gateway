package com.example.gateway.demo2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by hrhrh on 2020/7/10 15:03
 */
public class MyDeMO5 {

    public static void main(String[] args) {


        Map<String,String> map = new HashMap<>();

        map.put("a","b");
        map.put("c","d");
        map.put("e","f");
        map.put("g","h");
        map.put("i","j");

        Set<String> set = map.keySet();


        System.out.println(set);

        map.remove("a");

        System.out.println(set);
    }
}
