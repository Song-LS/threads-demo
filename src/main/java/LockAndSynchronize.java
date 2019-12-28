package main.java;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock和synchronize区别
 * lock：重入锁，是一个接口-实现类来为它提供各种功能，加锁的关键代码为大体为Lock和unLock，可调用ulock方法去释放锁比synchronized更灵活
 * synchronize：偏向锁，是java中的一个关键字，属于JVM会从在虚拟机指令层面加锁，不能指定解锁操作，执行完代码块的对象会自动释放锁
 * @author sls
 **/
public class LockAndSynchronize implements Runnable{
        final ReentrantLock lock = new ReentrantLock();

        static String source1 = "A";
        static String source2 = "B";

        public void run() {
            if(Thread.currentThread().getName().equals("1")) {
//                synchronized(source1) {
                    lock.lock();
                    System.out.println("线程1获取资源1锁");
                    try {
                        Thread.currentThread().sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.lock();
//                    synchronized(source2) {
                        System.out.println("线程1获取资源2锁");
                        try {
                            Thread.currentThread().sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
//                    }
//                }
                lock.unlock();
                lock.unlock();
            }else {
//                synchronized(source2) {
                    lock.lock();
                    System.out.println("线程2获取资源2锁");
                    try {
                        Thread.currentThread().sleep(200);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
//                    synchronized(source1) {
                        lock.lock();
                        System.out.println("线程2获取资源1锁");
                        try {
                            Thread.currentThread().sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        lock.unlock();
                        lock.unlock();
//                    }
//                }
            }
        }

        public static void main(String[] args) throws InterruptedException {
            LockAndSynchronize run = new LockAndSynchronize();
            new Thread(run,"1").start();
            new Thread(run,"2").start();
        }
}
