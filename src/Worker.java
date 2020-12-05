package com.company;

import java.util.concurrent.TimeUnit;

public class Worker implements Runnable {
    String name;
    final int timeoutInSeconds = 1;
    final int amountPerSecond = 3;
    int amountAll = 0;
    Mine mine;

    public Worker(String name) {
        this.name = name;
    }
    @Override
    public void run() {
        while (mine.amountLeft.get() > 0) {
            mine.performWork(this);
            try {
                TimeUnit.SECONDS.sleep(timeoutInSeconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(name + " finished");
    }
}
