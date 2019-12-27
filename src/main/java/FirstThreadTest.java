package main.java;

/**
 * @author sls
 **/
public class FirstThreadTest extends Thread {

    int i = 0;

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(this.getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 50) {
                new FirstThreadTest().start();
            }
        }
    }
}
