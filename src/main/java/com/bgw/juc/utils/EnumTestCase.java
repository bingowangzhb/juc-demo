package com.bgw.juc.utils;

/**
 * desc：
 *
 * @author wangzhb 2019/8/6 16:33
 */
public class EnumTestCase {
    public static void main(String[] args) {
        for (WeekEnum weekEnum : WeekEnum.values()) {
            System.out.println(weekEnum.getCode() + "\t " + weekEnum.getName());
        }
    }
}




enum WeekEnum {

    MONDAY(1, "周一"),
    TUESDAY(2, "周二"),
    WEDNESDAY(3, "周三"),
    THURSDAY(4, "周四"),
    FRIDAY(5, "周五"),
    SATURDAY(6, "周六"),
    SUNDAY(7, "周日");

    private Integer code;
    private String name;

    WeekEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
