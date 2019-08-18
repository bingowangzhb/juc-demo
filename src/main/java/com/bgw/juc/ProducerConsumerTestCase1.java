package com.bgw.juc;

import java.util.concurrent.TimeUnit;

/**
 * desc：synchronized & wait-notify 实现生产者消费者
 *
 * @author wangzhb 2019/8/5 12:37
 */
public class ProducerConsumerTestCase1 {

    public static void main(String[] args) {
        SyncShareObj shareObj = new SyncShareObj();
        for (int i = 1; i < 11; i++) {
            new Thread(shareObj::increment, "P-" + i).start();

            new Thread(shareObj::decrement, "C-" + i).start();
        }

    }
}

/**
 * 线程资源类
 */
class SyncShareObj {

    // 最大库存为 5
    private volatile int i = 0;

    public synchronized void increment() {
        while (i == 5) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        i++;
        System.out.println(Thread.currentThread().getName() + "\t 生产 i=" + i);

        this.notifyAll();
    }

    public synchronized void decrement() {
        while (i == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        i--;
        System.out.println(Thread.currentThread().getName() + "\t 消费 i=" + i);
        this.notifyAll();
    }

}