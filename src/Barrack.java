package com.company;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Barrack implements Runnable {
    final int produceWorkerTimeout = 10;
    int currentWorkerNumber = 0;
    boolean shouldStop = false;

    BlockingQueue<Worker> produceWorkersQueue;

    public Barrack(BlockingQueue<Worker> produceWorkersQueue) {
        this.produceWorkersQueue = produceWorkersQueue;
    }

    public Worker createWorker() {
        currentWorkerNumber++;
        return new Worker("Worker #" + currentWorkerNumber);
    }
    @Override
    public void run() {
        try {
            produceWorkersQueue.put(createWorker());
            produceWorkersQueue.put(createWorker());
            produceWorkersQueue.put(createWorker());
            produceWorkersQueue.put(createWorker());
            produceWorkersQueue.put(createWorker());
            while (!shouldStop) {
                TimeUnit.SECONDS.sleep(produceWorkerTimeout);
                produceWorkersQueue.put(createWorker());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
