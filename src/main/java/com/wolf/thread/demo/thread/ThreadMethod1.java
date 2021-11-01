package com.wolf.thread.demo.thread;

import com.wolf.thread.demo.task.MyTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class ThreadMethod1 {
    public static Logger logger = LoggerFactory.getLogger(ThreadMethod1.class);
    public static void main(String[] args) throws InterruptedException {
        MyTask firstTask = new MyTask("1");
        Thread thead1 = new Thread(firstTask);

        thead1.start();
        logger.info("thread1 started，{}", LocalDateTime.now());
        thead1.join();

        logger.info("thread1 end，{}", LocalDateTime.now());
    }
}
