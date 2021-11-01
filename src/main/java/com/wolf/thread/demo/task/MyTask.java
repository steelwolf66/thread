package com.wolf.thread.demo.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class MyTask implements Runnable {
    Logger logger = LoggerFactory.getLogger(getClass());

    private String taskId;

    public MyTask(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        try {
            logger.info("do task:【{}】 before",taskId);
            TimeUnit.SECONDS.sleep(1);
            logger.info("do task:【{}】 after",taskId);
        } catch (InterruptedException e) {
            logger.error("exception in task:", e);
        }
    }
}
