package com.bgw.juc.utils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * desc：集齐七颗龙珠召唤神龙
 *
 * @author wangzhb 2019/8/5 16:34
 */
public class CyclicBarrierTestCase {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("集齐七颗龙珠，召唤神龙");
        });

        for (int i = 1; i < 8; i++) {
            final int k = i;
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t 集齐龙珠" + k);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, "Thread-" + i).start();
        }
    }
}
