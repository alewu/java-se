package com.ale;

public class Son extends Parent{
    public static int sA = 1;

    public Son() {
        System.out.println("子类构造函数");
    }

    static {
        System.out.println("子类静态块");
    }


}
