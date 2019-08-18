package com.bgw.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * desc：验证volatile关键字特性
 *
 * @author wangzhb 2019/8/5 18:32
 */
public class VolatileTestCase {

    public static void main(String[] args) {

        // testVisibility();

        testNonAtomicity();
    }

    /**
     * volatile不保证原子性
     *
     * 1. 如果保证原子性i的结果=1000
     * 2. 解决：
     *   a. 使用synchronized修饰设置方法
     *   b. 使用juc包atomic类
     */
    public static void testNonAtomicity() {
        SharedData data = new SharedData();

        for (int i = 1; i < 11; i++) {
            new Thread(() -> {
                for (int j = 1; j < 1001; j++) {
                    data.increment();
                    data.atomicIncrement();
                }
            }, "Thread-" + i).start();
        }

        while(Thread.activeCount() > 2) {
            Thread.yield(); // 它会是当前线程让出CPU
        }

        System.out.println("i = " + data.getI());
        System.out.println("atomic = " + data.atomicInteger.get());
    }

    /**
     * volatile保证可见性
     *
     * 1. 资源类sharedData变量如果不加volatile 主线程将等待
     * 2. 添加volatile 主线程打印
     */
    public static void testVisibility() {
        SharedData data = new SharedData();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.setI(1);
            System.out.println(Thread.currentThread().getName() + "\t data.i=" + data.getI());
        }, "ThreadA").start();

        while (data.getI() == 0) {

        }

        System.out.println(Thread.currentThread().getName() + "\t data.i=" + data.getI());
    }
}

class SharedData {
    private volatile int i = 0;

    public AtomicInteger atomicInteger = new AtomicInteger(0);

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    /**
     * 2: getfield      #2                  // Field i:I
     * 5: iconst_1
     * 6: iadd
     * 7: putfield      #2                  // Field i:I
     *
     */
    public void increment() {
        this.i++;
    }

    public synchronized void synchronizedIncrement() {
        this.i++;
    }

    public void atomicIncrement() {
        this.atomicInteger.getAndIncrement();
    }


}




