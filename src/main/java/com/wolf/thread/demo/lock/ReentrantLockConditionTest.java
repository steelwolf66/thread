package com.wolf.thread.demo.lock;

import com.wolf.thread.demo.util.Sleeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockConditionTest {
    private static Logger logger = LoggerFactory.getLogger(ReentrantLockConditionTest.class);
    public static void main(String[] args) {
        ReentrantLockCondition reentrantLockCondition = new ReentrantLockCondition(1);
        //默认非公平锁
        ReentrantLock lock = new ReentrantLock();
        //创建三个condition，用来做线程通信
        Condition conditionFir = lock.newCondition();
        Condition conditionSec = lock.newCondition();
        Condition conditionThi = lock.newCondition();

        AtomicInteger loopNum = new AtomicInteger(1);


        new Thread(() -> {
            while (true) {
                Sleeper.sleep(1000);
                lock.lock();

                try {
                    if (reentrantLockCondition.getFlag() !=1) {
                        logger.info("wait");
                        conditionFir.await();
                    }
                    logger.info("A:{}", loopNum);
                    reentrantLockCondition.setFlag(2);
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
                Sleeper.sleep(1000);
                lock.lock();

                try {
                    if (reentrantLockCondition.getFlag() !=2) {
//                        logger.info("wait");

                        conditionSec.await();
                    }
                    logger.info("B:{}", loopNum);
                    reentrantLockCondition.setFlag(3);
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
                Sleeper.sleep(1000);
                lock.lock();

                try {
                    if (reentrantLockCondition.getFlag() !=3) {
//                        logger.info("wait");
                        conditionThi.await();
                    }
                    logger.info("C:{}", loopNum);
                    reentrantLockCondition.setFlag(1);
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
