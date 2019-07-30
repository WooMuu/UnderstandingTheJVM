package chapter13.lock.reentrantlock;

/**
 * Created by zjb on 2019/7/29.
 * 可重入锁的测试
 */
public class Test {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        lock.lock();
        print();
        lock.unlock();

    }

    private static void print() throws InterruptedException {
        lock.lock();
        System.out.println("我将会被打印...");
        lock.unlock();
    }
}
