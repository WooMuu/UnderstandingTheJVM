package chapter13;

/**
 * Created by zjb on 2019/7/29.
 */
public class SynchronizedReAntrend {
    public static void main(String[] args) {
        synchronized (SynchronizedReAntrend.class) {
            System.out.println("外部锁。。。");
            test1();
        }
    }

    private static void test1() {
        synchronized (SynchronizedReAntrend.class) {
            System.out.println("内部锁。。。");
        }
    }
}
