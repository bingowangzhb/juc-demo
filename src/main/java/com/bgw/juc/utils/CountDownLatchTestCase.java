package com.bgw.juc.utils;

import java.security.SecureRandom;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * desc：模拟赛跑
 *
 * @author wangzhb 2019/8/5 16:34
 */
public class CountDownLatchTestCase {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(8);
        Player player = new Player(countDownLatch);
        System.out.println("-----------------------开始赛跑----------------------");

        for (int i = 1; i < 9; i++) {
            new Thread(player::run, "Player-" + i).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------比赛结束---------------------------");
    }
}

class Player {
    private CountDownLatch countDownLatch;
    private SecureRandom random = new SecureRandom();
    public Player(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }
    public void run() {
        int s = random.nextInt(15) + 1;
        try {
            TimeUnit.SECONDS.sleep(s);
            System.out.println(Thread.currentThread().getName() + "\t 到达了终点，用了" + s + "秒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
        }
    }
}
