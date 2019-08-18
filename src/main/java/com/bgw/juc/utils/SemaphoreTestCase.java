package com.bgw.juc.utils;

import java.security.SecureRandom;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * desc：Semaphore 实现停车位停车场景 6部车3个车位
 *
 * @author wangzhb 2019/8/5 16:35
 */
public class SemaphoreTestCase {

    public static void main(String[] args) {
        // 3个车位
        Semaphore semaphore = new Semaphore(3);
        SecureRandom random = new SecureRandom();
        for (int i = 1; i < 7; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t 抢到停车位");
                    // 停车3秒

                    TimeUnit.SECONDS.sleep(random.nextInt(10) + 1);
                    System.out.println(Thread.currentThread().getName() + "\t 驶出停车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, "鲁A-" + i).start();
        }
    }
}
