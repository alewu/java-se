package com.ale;

public class Parent {
    public static int pA = 1;
    private int age;

    public Parent() {
        System.out.println("父类成员变量" + age);
        this.age = 1;
        System.out.println("父类构造函数" + pA);
    }


    static {
        System.out.println("父类静态代码块" + pA);
    }



}
