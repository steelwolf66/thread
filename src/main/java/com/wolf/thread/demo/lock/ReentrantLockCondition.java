package com.wolf.thread.demo.lock;

import com.wolf.thread.demo.util.Sleeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCondition {
    private static Logger logger = LoggerFactory.getLogger(ReentrantLockCondition.class);

    public static void main(String[] args) {

        //默认非公平锁
        ReentrantLock lock = new ReentrantLock();
        //创建三个condition，用来做线程通信
        Condition conditionFir = lock.newCondition();
        Condition conditionSec = lock.newCondition();
        Condition conditionThi = lock.newCondition();

        AtomicInteger atomicInteger = new AtomicInteger(0);
        AtomicInteger loopNum = new AtomicInteger(1);
        new Thread(() -> {
            while (true) {
                Sleeper.sleep(100);
                lock.lock();
                boolean isExcepted = atomicInteger.compareAndSet(0, 1);

                try {
                    if (!isExcepted) {
                        logger.info("wait");
                        conditionFir.await();
                    }
                    logger.info("A:{}", loopNum);
                    conditionSec.signal();
                } catch (InterruptedException e) {
                    logger.error("exception", e);
                } finally {
                    lock.unlock();
                }
            }
        }, "A").start();

        new Thread(() -> {
            while (true) {
                Sleeper.sleep(100);
                lock.lock();
                boolean isExcepted = atomicInteger.compareAndSet(1, 2);
                try {
                    if (!isExcepted) {
//                        logger.info("wait");

                        conditionSec.await();
                    }
                    logger.info("B:{}", loopNum);
                    conditionThi.signal();
                } catch (InterruptedException e) {

                    logger.error("exception", e);
                } finally {
                    lock.unlock();
                }
            }
        }, "B").start();

        new Thread(() -> {
            while (true) {
                Sleeper.sleep(100);
                lock.lock();
                boolean isExcepted = atomicInteger.compareAndSet(2, 0);
                try {
                    if (!isExcepted) {
//                        logger.info("wait");
                        conditionThi.await();
                    }
                    logger.info("C:{}", loopNum);
                    loopNum.getAndAdd(1);
                    logger.info("*****************************************");
                    conditionFir.signal();

                } catch (InterruptedException e) {

                    logger.error("exception", e);
                } finally {
                    lock.unlock();
                }
            }
        }, "C").start();
    }

}
