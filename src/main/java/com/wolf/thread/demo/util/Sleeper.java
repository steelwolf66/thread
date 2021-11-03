package com.wolf.thread.demo.util;

import java.util.concurrent.TimeUnit;

public class Sleeper {
    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
