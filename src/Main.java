package com.company;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Worker> queue = new ArrayBlockingQueue<>(5);

        Barrack barrack = new Barrack(queue);
        Thread t = new Thread(barrack);
        t.start();
        Mine mine = new Mine(1000, queue);
        mine.startWork();
        barrack.shouldStop = true;
        t.join();
    }
}
