package com.wolf.thread.demo.thread;

import com.wolf.thread.demo.task.MyTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadMethod2 {

private static Logger logger = LoggerFactory.getLogger(ThreadMethod2.class);
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new MyTask("t1"));
        t1.start();
        t1.join();
        logger.info("************");
        Thread t2 = new Thread(new MyTask("t2"));
        t2.start();
        t2.join();

    }
}
