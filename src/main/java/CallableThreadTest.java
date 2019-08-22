package main.java;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author sls
 **/
public class CallableThreadTest implements Callable<Integer> {

    public static void main(String[] args) {
        CallableThreadTest callableThreadTest = new CallableThreadTest();
        FutureTask<Integer> integerFutureTask = new FutureTask<>(callableThreadTest);
        for (int i=0; i<10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i ==5) {
                new Thread(integerFutureTask , "返回值").start();
            }
        }
        try {
            Integer integer = integerFutureTask.get();
            System.out.println("子线程返回值" + integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer call() throws Exception {
        int i=0;
        for (;i<10;i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        return i;
    }
}
