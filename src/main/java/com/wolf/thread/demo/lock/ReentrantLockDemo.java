package com.wolf.thread.demo.lock;

import com.wolf.thread.entity.User;

import java.lang.reflect.Array;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    public static void main(String[] args) {

        method1();

        method2();
    }

    private static void method1(){
       String a = new String ("original");
       String b = "b";

       a = a+b;

       String c = "originalb";

        System.out.println(a.equals(c));
        System.out.println(a == c);
    }

    private static void method2 (){


    }
}
