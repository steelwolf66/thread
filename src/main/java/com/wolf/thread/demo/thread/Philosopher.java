package com.wolf.thread.demo.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Philosopher extends Thread {
    private Chopstick left;
    private Chopstick right;

    public Philosopher(String name, Chopstick left, Chopstick right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    private static Logger logger = LoggerFactory.getLogger(Philosopher.class);
    @Override
    public void run() {
        synchronized (left){
            synchronized (right){
                logger.info("eating");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
