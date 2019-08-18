package com.bgw.juc;

import java.util.concurrent.TimeUnit;

/**
 * desc：
 *
 * @author wangzhb 2019/8/5 11:21
 */
public class InterruptTestCase {

    public static void main(String[] args) {
        Thread sleepThd = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        Thread busyThd = new Thread(() -> {
            while (true) {
//                if (Thread.currentThread().isInterrupted()) {
//                    System.out.println("我被中断了");
//                    break;
//                }
            }
        });

        sleepThd.start();
        busyThd.start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("sleepThd after start Interrupted : " + sleepThd.isInterrupted());
        System.out.println("busyThd after start Interrupted : " + busyThd.isInterrupted());
        System.out.println("----------------------------------------------------------");

        sleepThd.interrupt();
        busyThd.interrupt();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("sleepThd after interrupt Interrupted : " + sleepThd.isInterrupted());
        System.out.println("busyThd after Interrupted : " + busyThd.isInterrupted());


    }



}
