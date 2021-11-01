package com.wolf.thread.demo.reference;

import com.wolf.thread.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReferenceDemo {
    private static Logger logger = LoggerFactory.getLogger(ReferenceDemo.class);
    public static void main(String[] args) {
    int a = 10;
    method1(a);
    logger.info("main a :{}",a);

    User parparamUser = new User();
    parparamUser.setName("mainUser");
    method2(parparamUser);
    logger.info("main user:{}",parparamUser);
    }

    public static void method1(int paramIn){
        paramIn = paramIn+1;
        logger.info("method1 result :{}",paramIn);
    }

    public static void method2(User paramUser){
        paramUser.setName("new Name");
    }
}
