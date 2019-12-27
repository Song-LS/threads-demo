package main.java;

/**
 * @author sls
 **/
public class RunnableThreadTest implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 5) {
                RunnableThreadTest threadTest = new RunnableThreadTest();
                new Thread(threadTest, "111").start();
                new Thread(threadTest, "222").start();
                new Thread(threadTest, "333").start();
            }
        }
    }
}
