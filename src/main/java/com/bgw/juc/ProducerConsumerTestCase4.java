package com.bgw.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * desc：Semaphore 实现生产者消费者
 *
 * @author wangzhb 2019/8/5 12:37
 */
public class ProducerConsumerTestCase4 {

    public static void main(String[] args) {
        SemaphoreShareObj shareObj = new SemaphoreShareObj(0, 10);
        for (int i = 1; i < 11; i++) {

            new Thread(() -> {
                while (true) {
                    shareObj.consume();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "C-" + i).start();

            new Thread(() -> {
                while (true) {
                    shareObj.produce();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }, "P-" + i).start();
        }

    }
}


class SemaphoreShareObj {

    // 库存
    private volatile int i = 0;

    private int maxSize;

    private Semaphore notFull;

    private Semaphore notEmpty;

    private Semaphore mutex;

    public SemaphoreShareObj(int i, int maxSize) {
        this.i = i;
        this.maxSize = maxSize;
        notEmpty = new Semaphore(0);
        notFull = new Semaphore(maxSize);
        mutex = new Semaphore(1);
    }

    public void produce() {
        try {
            // 保证不满
            notFull.acquire();
            // 保证互斥
            mutex.acquire();
            i++;
            System.out.println(Thread.currentThread().getName() + "\t 生产 i =" + i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mutex.release();
            notFull.release();
        }
    }

    public void consume() {
        try {
            // 保证不空
            notEmpty.acquire();
            // 保证互斥
            mutex.acquire();
            i--;
            System.out.println(Thread.currentThread().getName() + "\t 消费 i =" + i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mutex.release();
            notEmpty.release();
        }
    }


}

