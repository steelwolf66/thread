package com.wolf.thread.demo.singletone;

/**
 * double check lock
 * 懒汉式 单例模式
 */
public class DCLSingleton {
    private static volatile DCLSingleton instanse; //防止指令重排

    public static DCLSingleton getInstanse() {
        if (instanse == null) {
            //因为只有第一次进入这个方法时，创建instanse,所以synchronized保护的同步代码块要尽可能的少
            synchronized (DCLSingleton.class) {
                instanse = new DCLSingleton(); //可能出现指令重排
            }
        }
        return instanse;
    }
}
