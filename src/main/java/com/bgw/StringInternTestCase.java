package com.bgw;

import java.util.ArrayList;

/**
 * desc：
 *
 * @author wangzhb 2019/8/7 22:21
 */
public class StringInternTestCase {

    public static void main(String[] args) {
//        String s = new String("abc"); // 创建两个对象一个是s 一个常量池中的abc
//        String s2 = "abc";
//
//        System.out.println(s == s2);
//        System.out.println(s.intern() == s2);

        test6();
    }





    private static void test5() {
        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2); // false

        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);
    }

    private static void test6() {
        String s = new String("1");
        String s2 = "1";
        s.intern();
        System.out.println(s == s2);// false

        String s3 = new String("1") + new String("1");
        String s4 = "11";
        s3.intern();
        System.out.println(s3 == s4);
    }


    private static void test4() {
        String s = "1";
        String s2 = new String("1");
        String s3 = "1";

        System.out.println(s == s2);

    }

    private static void test3() {
        String s1 = new String("s");
        System.out.println(s1 == s1.intern());

        String s2 = new StringBuilder("s2").append("b").toString();
        System.out.println(s2 == s2.intern());

        String s3 = new String("s3b");
        System.out.println(s3 == s3.intern());
    }


    private static void test2() {
        String s1 = new StringBuilder("漠").append("然").toString();
        System.out.println(s1.intern() == s1);

        String s2 = new StringBuilder("漠").append("然").toString();

        System.out.println(s2.intern() == s1);
        System.out.println(s2.intern().hashCode());
        System.out.println(s2.hashCode());

        // System.out.println(s2.intern() ==);
    }

    private static void test1() {
        String a = "123";
        String b = "123";

        String c = new String("123");

        System.out.println(a.intern() == b.intern());
        System.out.println(a == b);
        System.out.println(a.intern() == c.intern());
        System.out.println(a == c);

        System.out.println(a.intern());
        System.out.println(c.intern());
    }
}
