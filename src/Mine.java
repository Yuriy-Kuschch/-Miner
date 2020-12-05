package com.company;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Mine {
    AtomicInteger amountLeft;

    private BlockingQueue<Worker> queue;

    public Mine(int amountLeft, BlockingQueue<Worker> queue) {
        this.amountLeft = new AtomicInteger(amountLeft);
        this.queue = queue;
    }

    public void startWork() throws InterruptedException {
        while (amountLeft.get() > 0) {
            Worker worker = queue.take();
            worker.mine = this;
            new Thread(worker).start();
        }
    }
    public synchronized void performWork(Worker worker) {
        if (amountLeft.get() > worker.amountPerSecond) {
            worker.amountAll += worker.amountPerSecond;
            amountLeft.getAndAdd(-worker.amountPerSecond);
        } else if (amountLeft.get() > 0) {
            worker.amountAll += amountLeft.get();
            amountLeft.set(0);
        } else {
            return;
        }
        System.out.println(worker.name + " produced " + worker.amountAll);
        System.out.println("On mine left " + amountLeft.get());
    }
}
