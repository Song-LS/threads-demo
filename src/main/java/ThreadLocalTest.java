package main.java;

/**
 * @author sls
 **/
public class ThreadLocalTest {

//    private static final ThreadLocal<Integer> THREAD_LOCAL_NUM = new ThreadLocal<Integer>() {
//        @Override
//        protected Integer initialValue() {
//            return 0;
//        }
//    };
    private static final ThreadLocal<Integer> THREAD_LOCAL_NUM = ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
//            Thread t = new Thread() {
//                @Override
//                public void run() {
//                    add10ByThreadLocal();
//                }
//            };
            Thread t = new Thread(() -> add10ByThreadLocal());
            t.start();
        }
    }

    private static void add10ByThreadLocal() {
        for (int i = 0; i < 5; i++) {
            Integer n = THREAD_LOCAL_NUM.get();
            n += 1;
            THREAD_LOCAL_NUM.set(n);
            System.out.println(Thread.currentThread().getName() + ": thread local num = " + n);
        }
        System.out.println("sss");
    }

}
