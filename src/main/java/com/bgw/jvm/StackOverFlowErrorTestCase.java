package com.bgw.jvm;

/**
 * desc：
 *
 * @author wangzhb 2019/8/8 14:35
 */
public class StackOverFlowErrorTestCase {

    public static void main(String[] args) {
        stackOverFlowError();
    }

    private static void stackOverFlowError() {
        stackOverFlowError();
    }
}
