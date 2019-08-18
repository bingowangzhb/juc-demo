package com.bgw.jvm;

/**
 * desc：
 *
 * @author wangzhb 2019/8/8 12:30
 */
public class GcTestCase {

    public static void main(String[] args) {

        long totalMemory = Runtime.getRuntime().totalMemory(); // JVM内存总量
        long maxMemory = Runtime.getRuntime().maxMemory(); // JVM试图使用的最大内存

        System.out.println("totalMemory: " + totalMemory / 1024 / 1024 + "M");
        System.out.println("maxMemory: " + maxMemory / 1024 / 1024 + "M");

        byte[] b = new byte[50 * 1024 * 1024];



    }
}
