package com.wolf.thread.demo.thread;

public class PhilosopherDemo {
    public static void main(String[] args) throws InterruptedException {
        Chopstick chopstick1 = new Chopstick("1");
        Chopstick chopstick2 = new Chopstick("2");
        Chopstick chopstick3 = new Chopstick("3");
        Chopstick chopstick4 = new Chopstick("4");
        Chopstick chopstick5 = new Chopstick("5");
        while (true) {
            new Philosopher("阿基米德", chopstick1, chopstick2).start();
            new Philosopher("伽利略", chopstick3, chopstick4).start();
            new Philosopher("华罗庚", chopstick2, chopstick3).start();
            new Philosopher("焦耳", chopstick5, chopstick1).start();
            new Philosopher("安培", chopstick4, chopstick5).start();
            Thread.sleep(10);
        }
    }
}
