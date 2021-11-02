package com.wolf.thread.demo.thread;

import com.wolf.thread.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadWaitNotify {
    private static Logger logger = LoggerFactory.getLogger(ThreadWaitNotify.class);
    private static User user = new User();

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();


        Thread t1 = new Thread(() -> {
            try {
                synchronized (user) {
                    user.setName("name");
                    user.wait();
                    logger.info("t1 waiting");
                }
                logger.info("t1 completed");

            } catch (InterruptedException e) {
               logger.error("exception:",e);
            }
        }, "t1");


        Thread t2 = new Thread(() -> {
            synchronized (user) {
                user.notifyAll();
                logger.info("notify thread t1");
            }
            logger.info("thread2 completed");
        }, "t2");

        t1.start();
        logger.info("t1 started");
        t2.start();

    }
}