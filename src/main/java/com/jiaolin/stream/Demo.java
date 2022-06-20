package com.jiaolin.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author johnny
 * @Classname Demo
 * @Description 进行流式计算+函数式接口 4大 +链式编程
 * @Date 2022/3/29 17:49
 */
public class Demo {
    // /**
    // * @create 2019-02-26 22:24
    // *
    // * 题目：请按照给出数据，找出同时满足
    //*      偶数ID且年龄大于24且用户名转为大写且用户名字母倒排序
    //*      最后只输出一个用户名字
    //*/
    public static void main(String[] args) {
        User user1 = new User(11, "a", 23);
        User user2 = new User(12, "b", 24);
        User user3 = new User(13, "c", 22);
        User user4 = new User(14, "d", 28);
        User user5 = new User(16, "e", 26);
        List<User> list = Arrays.asList(user1, user2, user3, user4, user5);
        list.stream().filter((a) -> {
            return a.getId() % 2 == 0;
        }).filter((b) -> {
            return b.getAge() > 24;
        }).map((c) -> {
            return c.getName().toUpperCase();
        }).sorted((o1, o2) -> {
            return o2.compareTo(o1);
        }).limit(1).forEach(System.out::println);

    }
}


class User {
    private int id;
    private String name;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}