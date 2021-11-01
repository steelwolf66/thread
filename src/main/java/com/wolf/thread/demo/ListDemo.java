package com.wolf.thread.demo;

import java.util.ArrayList;
import java.util.List;

public class ListDemo {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<String>(8) {
            {
                add("qiaobei");
                add("gongyue");
                add("zhaoxinrui");
                add("zhangjiawen");
            }
        };

        stringList.spliterator().trySplit().forEachRemaining(item ->{
            item = "saobi";
        });
        System.out.println(stringList);
    }
}
