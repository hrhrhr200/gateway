package com.example.gateway.demo;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by hrhrh on 2020/6/12 15:48
 */
public class ThreadDemo8 {

   private final static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

       for(int i =0;i<10;i++) {
          new Thread(()->{
             synchronized (lock){
                System.out.println(Thread.currentThread().getName() +"阻塞住啦啦啦啦");
                try {
                   TimeUnit.SECONDS.sleep(1);
                   lock.wait();
                } catch (InterruptedException e) {
                   e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() +"醒啦啦啦啦");
             }
          },"t"+i).start();
       }

       new Thread(() -> {
          try {
             TimeUnit.SECONDS.sleep(11);
          } catch (InterruptedException e) {
             e.printStackTrace();
          }
          synchronized (lock) {
             System.out.println("主要线程完成");
             lock.notifyAll();
          }
       },"t111").start();
    }




}
