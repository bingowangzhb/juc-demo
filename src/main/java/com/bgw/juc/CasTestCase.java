package com.bgw.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * desc：
 *
 * @author wangzhb 2019/8/6 9:45
 */
public class CasTestCase {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        System.out.println(atomicInteger.get());
        atomicInteger.compareAndSet(0, 78);
        System.out.println(atomicInteger.get());

        atomicInteger.compareAndSet(23, 24);
        System.out.println(atomicInteger.get());

        atomicInteger.getAndIncrement();

    }
}
