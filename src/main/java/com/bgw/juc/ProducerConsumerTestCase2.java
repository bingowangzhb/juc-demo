package com.bgw.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * desc：Lock & await signal 实现生产者消费者
 *
 * @author wangzhb 2019/8/5 12:37
 */
public class ProducerConsumerTestCase2 {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        ConditionShareObj shareObj = new ConditionShareObj(lock);
        for (int i = 1; i < 11; i++) {
            new Thread(shareObj::increment, "P-" + i).start();

            new Thread(shareObj::decrement, "C-" + i).start();
        }

    }
}

/**
 * 线程资源类
 */
class ConditionShareObj {

    private Lock lock;

    private Condition condition;

    // 最大库存
    private static final int MAX_SIZE = 5;

    // 初始库存
    private volatile int i = 0;


    public ConditionShareObj(Lock lock) {
        this.lock = lock;
        condition = this.lock.newCondition();
    }

    public void increment() {
        lock.lock();
        try {
            while (i == MAX_SIZE) {
                condition.await();
            }
            TimeUnit.SECONDS.sleep(1);

            i++;
            System.out.println(Thread.currentThread().getName() + "\t 生产 i=" + i);

            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() {
        lock.lock();
        try {
            while (i == 0) {
                condition.await();
            }

            TimeUnit.SECONDS.sleep(1);

            i--;
            System.out.println(Thread.currentThread().getName() + "\t 消费 i=" + i);

            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}