package com.bgw.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * desc：
 *
 * @author wangzhb 2019/8/7 10:01
 */
public class ConditionTestCase {

    public static void main(String[] args) {
        PrintService printService = new PrintService();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                printService.print5();
            }
        }, "ThreadA").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                printService.print10();
            }
        }, "ThreadB").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                printService.print15();
            }
        }, "ThreadC").start();
    }

}

class PrintService {
    private volatile int flag = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
            while (flag != 1) {
                c1.await();
            }

            for (int j = 1; j < 6; j++) {
                System.out.println(Thread.currentThread().getName() + "\t " + j);
            }
            flag = 2;
            c2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            while (flag != 2) {
                c2.await();
            }

            for (int j = 1; j < 11; j++) {
                System.out.println(Thread.currentThread().getName() + "\t " + j);
            }
            flag = 3;
            c3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            while (flag != 3) {
                c3.await();
            }

            for (int j = 1; j < 16; j++) {
                System.out.println(Thread.currentThread().getName() + "\t " + j);
            }
            flag = 1;
            c1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}