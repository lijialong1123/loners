package com.example.demo.thread;


import java.util.concurrent.atomic.AtomicInteger;

public class DeadLockDemo {

    private static final String A = "A";
    private static final String B = "B";

    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger();
        i.incrementAndGet();
        new DeadLockDemo().deadLock();
    }

    private void deadLock() {
        Thread t1 = new Thread(() -> {
            synchronized (A) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B) {
                    System.out.println("1");
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (B) {
                synchronized (A) {
                    System.out.println("2");
                }
            }
        });
        t1.start();
        t2.start();
    }


}
