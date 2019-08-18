package com.bgw.juc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

/**
 * desc：
 *
 * @author wangzhb 2019/8/6 10:29
 */
public class AtomicReferenceTestCase {
    public static void main(String[] args) {
        AtomicReference<User> reference = new AtomicReference<>();

        User u1 = new User("james", 12);
        User u2 = new User("kevin", 33);

        reference.set(u1);

        System.out.println(reference.compareAndSet(u1, u2) + "\t " + reference.get().toString());
        System.out.println(reference.compareAndSet(u1, u2) + "\t " + reference.get().toString());
    }
}


@Data
@ToString
@AllArgsConstructor
class User {
    private String name;
    private Integer age;
}