package chapter12;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.StampedLock;

/**
 * Created by zjb on 2019/7/26.
 */
public class Test {
    private static final StampedLock LOCK = new StampedLock();

    public static void main(String[] args)  {
        Lock lock = LOCK.asWriteLock();
        lock.lock();
        int a = 1;
        lock.unlock();
    }
}
