package main.java;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author sls
 **/
public class MyThread extends Thread {

    private Object co;
    private String name;

    MyThread(String name, Object co) {
        this.name = name;
        this.co = co;

    }

    @Override
    public void run() {
        System.out.println(this.getName() + name + "is waiting");
        try {
            synchronized (co) { // 将改对象锁上
                co.wait(); // 线程休眠
            }
            System.out.println(name + "has bean notified");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Date date = new Date(System.currentTimeMillis());
        while (true) {
            System.out.println(new SimpleDateFormat("HH:mm:ss").format(date));
            date = new Date(date.getTime() + 1000);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
