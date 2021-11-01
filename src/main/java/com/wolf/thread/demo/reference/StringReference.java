package com.wolf.thread.demo.reference;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringReference {
    private static Logger logger = LoggerFactory.getLogger(StringReference.class);

    public static void main(String[] args) {
        String paramString = "init";
        method1(paramString);
        logger.info("after method string:{}", paramString);
    }

    public static void method1(String param) {
        param = param.concat("new");
        logger.info("new string :{}", param);
    }
}