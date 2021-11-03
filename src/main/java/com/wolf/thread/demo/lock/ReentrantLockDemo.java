package com.wolf.thread.demo.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    private static ReentrantLock lock = new ReentrantLock();
    private static Logger logger = LoggerFactory.getLogger(ReentrantLockDemo.class);

    public static void main(String[] args) {
        new Thread(() -> {
            logger.info("invoke method1");
            method1();
        }, "t1").start();

        new Thread(() -> {
            logger.info("invoke method2");
            method2(lock);
        }, "t2").start();
    }

    private static void method1() {
        lock.lock();
        try {
            Thread.sleep(1000);
            logger.info("method 1");
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    private static void method2(ReentrantLock lock) {
        //尝试获得锁,并设置超时时间
        boolean releaseLock = false;
        try {


            boolean locked = lock.isLocked();
            logger.info("lock is locked:{}",locked);
            releaseLock = lock.tryLock(1/2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            logger.error("exception ", e);
        }
        if (!releaseLock) {
            logger.info("can't get lock");
            return;
        }
        //执行到这里，说明已经获取到锁了，要记得在finally中释放锁
        try {
            logger.info("method 2");
        } catch (Exception e) {
            logger.info("exception", e);
        }finally {
            lock.unlock();
        }

    }
}
