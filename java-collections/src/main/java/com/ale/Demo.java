package com.ale;


import java.util.ArrayList;
import java.util.List;

public class Demo {
    static {
        System.out.println("demo");
    }

    public static void main(String[] args) {
        //用于存放模拟的对象，防止GC回收，用List做对象引用
        List<Parent> list = new ArrayList<>();
        for (; ; ) {
            list.add(new Parent());
        }
    }
}
