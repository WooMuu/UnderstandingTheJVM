package chapter12;

import java.util.concurrent.CountDownLatch;

/**
 * Created by zjb on 2019/7/26.
 * volatile关键字不保证程序的原子性，
 */
public class VolatileTest2 {
    private static volatile int i = 0;

    private static final CountDownLatch LATCH = new CountDownLatch(10);

    private static void inc() {
        i++;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int j = 0; j < 10; j++) {
            new Thread(() -> {
                for (int k = 0; k < 1000; k++) {
                    inc();
                }
                LATCH.countDown();
            }).start();
        }
        LATCH.await();
        System.out.println(i);
    }
}
