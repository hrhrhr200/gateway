package com.example.gateway.demo.phaserdemo;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Created by hrhrh on 2020/6/6 16:17
 */
public class PhaserDemo {

    static Random r = new Random();
    static MarriagePhaser m = new MarriagePhaser();

/*    static void millsleep(int mill) {
        try {
            TimeUnit.MILLISECONDS.sleep(mill);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/


    static class MarriagePhaser extends Phaser {
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase) {
                case 0:
                    System.out.println("所有人都到齐了! " + registeredParties);
                    return false;
                case 1:
                    System.out.println("所有人都吃完了! "+ registeredParties);
                    return false;
                case 2:
                    System.out.println("所有人都离开了！" + registeredParties);
                    return false;
                case 3:
                    System.out.println("婚礼结束！新郎新娘抱抱" + registeredParties);
                    return true;
                default:
                    return true;
            }
        }
    }


    static class Person implements Runnable {
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
            m.arriveAndAwaitAdvance();
        }

        public void eat() {
            try {
                TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s 吃完! \n", name);
            m.arriveAndAwaitAdvance();
        }

        public void leave() {
            try {
                TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s 到达现场! \n", name);
            m.arriveAndAwaitAdvance();
        }

        public void hug() {
            if(name.equals("新郎") || name.equals("新娘")) {
                try {
                    TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("%s 洞房! \n", name);
                m.arriveAndAwaitAdvance();
            }else {
                m.arriveAndDeregister();
            }
        }

        @Override
        public void run() {
            arrive();
            eat();
            leave();
            hug();
        }
    }

    public static void main(String[] args) {
        m.bulkRegister(7);

        for(int i = 0;i<5;i++) {
            new Thread(new Person("p"+i)).start();
        }

        new Thread(new Person("新郎")).start();
        new Thread(new Person("新娘")).start();
    }
}
