package com.bgw.juc;

/**
 * desc：指令重排 编译器优化->指令并行重拍->内存系统重拍
 *
 * @author wangzhb 2019/8/5 20:21
 */
public class ResortTestCase {
    int a = 0;
    boolean flag = false;

    public void m1() {
        a = 1; // 1
        flag = true; // 2
    }

    public void m2() {
        if (flag) {
            a += 5;
            System.out.println("a = " + a); // 1和2的重排序可能导致 a = 6 or a = 5
        }
    }
}
