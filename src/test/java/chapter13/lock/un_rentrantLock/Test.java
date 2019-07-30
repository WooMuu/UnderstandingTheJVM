package chapter13.lock.un_rentrantLock;

/**
 * Created by zjb on 2019/7/29.
 */
public class Test {
    private static UnReentrantLock lock = new UnReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        lock.lock();
        print();
        lock.unlock();

    }

    private static void print() throws InterruptedException {
        lock.lock();
        System.out.println("我将永远不会被打印...");
        lock.unlock();
    }
}
