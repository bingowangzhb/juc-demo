package com.bgw.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * desc：
 *
 * @author wangzhb 2019/8/7 17:27
 */
public class StringOomTestCase {
    static String base = "string";

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String str = base + base;
            base = str;
            list.add(str.intern());
        }
    }
}
