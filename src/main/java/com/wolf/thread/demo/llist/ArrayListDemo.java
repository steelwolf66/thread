package com.wolf.thread.demo.llist;

import java.util.Arrays;
import java.util.stream.Stream;

public class ArrayListDemo {
    public static void main(String[] args) {
        String paramString = "01";
        String configValue = "0801,0802,0803,1101";
        boolean isMatch = Arrays.asList(configValue.split(",")).parallelStream().anyMatch(item -> {
            return item.equalsIgnoreCase(paramString);
        });
        Stream<String> stringStream = Arrays.asList(configValue.split(",")).parallelStream();
        System.out.println("don't has total :"+isMatch);
        System.out.println(stringStream);
    }
}
