package main.java;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock形成死锁
 * @author sls
 **/
public class LockDead implements Runnable{
        /*
        死锁产生条件：
        1、 互斥条件
        2、 请求与保持条件
        3、 不可剥夺条件
        4、 循环等待条件
         */

        private boolean flag;
        private static ReentrantLock lock1=new ReentrantLock();
        private static ReentrantLock lock2=new ReentrantLock();

        public LockDead(boolean flag) {
            this.flag = flag;
        }

        @Override
        public void run() {
            try {
                if(flag){
                    lock1.lock();
                    System.out.println(flag + "线程获取了Lock1");
                    Thread.currentThread().sleep(100);
                    lock2.lock();
                    System.out.println(flag+"线程获取了Lock2");
                }else{
                    lock2.lock();
                    System.out.println(flag + "线程获取了Lock2");
                    Thread.currentThread().sleep(100);
                    lock1.lock();
                    System.out.println(flag+"线程获取了Lock1");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if(lock1.isHeldByCurrentThread()){
                    lock1.unlock();
                }
                if(lock2.isHeldByCurrentThread()){
                    lock2.unlock();
                }
            }
        }

        public static void main(String[] args) throws InterruptedException {
            Thread thread1=new Thread(new LockDead(true));
            Thread thread2=new Thread(new LockDead(false));
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
            System.out.println("主线程已结束");
        }
}
