package com.wolf.thread.demo.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 统筹规划代码简单实现
 */
public class ThreadPlan1 {
    public static Logger logger = LoggerFactory.getLogger(ThreadPlan1.class);

    /**
     * 华罗庚<统筹规划>问题
     * 泡茶问题
     * 洗水壶 （1分钟）-》烧水 （5分钟）
     * 洗水杯（1分钟） -》取茶叶（1分钟） -》泡茶（2分钟）
     * 最后喝茶
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(()->{
            logger.info("t1 洗水壶");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.error("sleep interrrupted exception",e);
            }
            logger.info("**************** t1 水壶洗好了");

            logger.info("t1 烧水");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                logger.error("sleep interrrupted exception",e);
            }
            logger.info("**************** t1 水开了");

        });

        Thread t2 = new Thread(()->{
            logger.info("t2 洗水杯");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.error("sleep interrrupted exception",e);
            }
            logger.info("************** t2 水杯洗好了");

            logger.info("t2 取茶叶");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.error("sleep interrrupted exception",e);
            }
            logger.info("**************** t2 茶叶取好了");
        });

        t1.start();
        t2.start();

        t1.join();
        logger.info("主线程 泡茶");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            logger.error("sleep interrrupted exception",e);
        }
        logger.info("**************** 主线程茶泡好了，可以喝了");
    }
}
