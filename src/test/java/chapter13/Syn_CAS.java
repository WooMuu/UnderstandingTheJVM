package chapter13;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zjb on 2019/7/30.
 * 对比synchronized与CAS的效率
 * synchronized测试结果
 * 线程数    4      8    16    32    64
 * 平均时间  78    135   173   218   219
 * <p>
 * CAS
 * 线程数    4      8      16    32     64
 * 平均时间  78     94     100   122    158
 */
public class Syn_CAS {
    public static AtomicInteger race = new AtomicInteger(0);
//    public static int race = 0;

    public static void increace() {
        race.incrementAndGet();
    }

    private static final int THREADS_COUNT = 64;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    increace();
                }
            }, "increase" + i);
            threads[i].start();
        }
        while (Thread.activeCount() > 2) {//除main线程还有一个Monitor Ctrl-Break线程活动
            Thread.yield();
        }

        System.out.println(race);
        System.out.println(System.currentTimeMillis() - start);
    }
}
