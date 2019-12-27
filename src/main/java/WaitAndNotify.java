package main.java;

import java.util.concurrent.TimeUnit;

/**
 * @author sls
 **/
public class WaitAndNotify {

    public static void main(String[] args) {
        Object co = new Object();
        System.out.println(co);

        for (int i = 0; i < 10; i++) {
            MyThread myThread = new MyThread("thread-" + i, co);
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myThread.start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("-----  main thread notified------");
            synchronized (co) {
                co.notify(); // 唤醒单个线程
//                co.notifyAll(); // 唤醒此对象上的所有线程
            }
            TimeUnit.SECONDS.sleep(2);
            System.out.println("------ main thread end------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
