package com.wolf.thread.demo.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReentrantLockCondition {
    private static Logger logger = LoggerFactory.getLogger(ReentrantLockCondition.class);
    public int flag;

    public ReentrantLockCondition(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
