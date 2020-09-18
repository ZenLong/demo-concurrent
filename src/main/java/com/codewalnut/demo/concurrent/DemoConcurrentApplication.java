package com.codewalnut.demo.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DemoConcurrentApplication {
    private static Logger log = LoggerFactory.getLogger(DemoConcurrentApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoConcurrentApplication.class, args);

        testSemaphoreJob();
    }

    public static void testSemaphoreJob() {
        int totalJob = 100;
        log.info("模拟100个任务排队，但通过信号量控制同一时间最多5个任务运行");

        for (int i = 0; i < totalJob; i++) {
            Thread thread = new Thread(new SemaphoreJob());
            thread.start();
        }
    }

}
