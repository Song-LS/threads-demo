package main.java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 三种线程池
 * 1.Executors.newSingleThreadExecutor()：只能存在一个线程，这个线程死后另外一个线程会补上
 * 2.Executors.newFixedThreadPool(100)：定长的线程池，线程定为100个，而循环只有10个，那么也只会用到前10个进程
 * 3.Executors.newCachedThreadPool()：线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程
 * 4.Executors.newScheduledThreadPool(5)：创建一个定长线程池，支持定时及周期性任务执行
 * @author sls
 **/
public class ThreadPool {

    public static void main(String[] args) {
        // newCachedThreadPool:
        // 创建一个单线程的线程池,单线程串行执行所有任务,如果这个唯一的线程因为异常结束，那么会有一个新的线程来替代它
        // 此线程池保证所有任务的执行顺序按照任务的提交顺序执行.
        // 按顺序来执行线程任务 但是不同于单线程，这个线程池只是只能存在一个线程，这个线程死后另外一个线程会补上
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            newSingleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
//                    System.out.println(Thread.currentThread().getName() + " " + index);
                }
            });
        }
        ////////////////////////////////////////


        // 创建一个定长的线程池，也是根据需要去调用线程，比如线程定为100个，而循环只有10个，那么也只会用到前10个进程。
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            newFixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
//                    System.out.println(Thread.currentThread().getName() + "---" + index);
                }
            });
        }

        ////////////////////////////////////////

        // 线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
        // 这里如果去除sleep，则你会发现在这个循环中创建了新的线程，因为前一个任务没有执行完，所以创建新线程执行下一个任务。
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            newCachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
//                    System.out.println(Thread.currentThread().getName() + "----" +index);
                }
            });
        }

        //////////////////////////////////////
        // 创建一个定长线程池，支持定时及周期性任务执行。
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 10; i++) {
            newScheduledThreadPool.schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "delay 3");
                }
            }, 3, TimeUnit.SECONDS);
        }

        // 使用Lambda
        for (int i = 0; i < 10; i++) {
            newScheduledThreadPool.schedule(()-> System.out.println(Thread.currentThread().getName()+"delay 4"),3, TimeUnit.SECONDS);
        }

    }
}
