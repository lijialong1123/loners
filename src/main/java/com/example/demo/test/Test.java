package com.example.demo.test;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.*;

/**
 * @author : coder
 * @create 2020/11/29 14:09
 */
public class Test {

    public static final ThreadFactory factory = new ThreadFactoryBuilder().setNamePrefix("demo-pool").build();
    public static final ExecutorService executorService = new ThreadPoolExecutor(5, 10, 0L, TimeUnit.MICROSECONDS, new LinkedBlockingDeque<>(1024), factory,
            new ThreadPoolExecutor.AbortPolicy());

    volatile boolean running = true;

    void m() {
        System.out.println(" m  start");
        while (running) {

        }
        System.out.println(" m end");
    }

    public static void main(String[] args) {
        Test t = new Test();
        executorService.submit(t::m);
//        new Thread(t::m, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.running = false;
    }

}
