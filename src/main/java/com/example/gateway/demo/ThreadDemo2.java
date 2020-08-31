package com.example.gateway.demo;

import java.util.concurrent.TimeUnit;

/**
 * Created by hrhrh on 2020/6/6 10:45
 */
public class ThreadDemo2 {

   int count = 0;

   synchronized void m() {
       System.out.println(Thread.currentThread().getName() + "start");

       while (true) {
           count++;
           System.out.println(Thread.currentThread().getName()+ "count =" + count);
           try {
               TimeUnit.SECONDS.sleep(1);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }

           if(count == 5) {
               try{int i = 1/0;}
              catch (Exception e) {
                   e.printStackTrace();
                   break;
              }
                    //break;
               //System.out.println(i);
           }
       }
   }

    public static void main(String[] args) {
        ThreadDemo2 td = new ThreadDemo2();

        Runnable r = td::m;

        new Thread(r, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(r, "t2").start();
    }
}
