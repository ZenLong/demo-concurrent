package com.codewalnut.demo.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.Semaphore;


/**
 * 以信号量Semaphore控制并发的线程数
 */
public class SemaphoreJob implements Runnable {
    private static Logger log = LoggerFactory.getLogger(DemoConcurrentApplication.class);

    private static Semaphore semaphore = new Semaphore(5);
    private String jobId = UUID.randomUUID().toString();

    @Override
    public void run() {
        try {
            log.info("Job {} waiting... {} available", jobId, semaphore.availablePermits(), semaphore.getQueueLength());
            semaphore.acquire(1);
            log.info("Job {} start... {} queued", jobId, semaphore.getQueueLength());
            Thread.sleep(5000);
            log.info("Job {} finished", jobId);
        } catch (InterruptedException e) {
            log.error("Failed", e);
        } finally {
            semaphore.release(1);
        }
    }
}
