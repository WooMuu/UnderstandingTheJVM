package chapter13.lock.reentrantlock;

/**
 * Created by zjb on 2019/7/29.
 * 可重入锁
 *
 */
public class ReentrantLock {
    private boolean isLock = false;

    private Thread lockBy = null;

    int lockCount = 0;

    public synchronized void lock() throws InterruptedException {
        Thread thread = Thread.currentThread();
        while (isLock && thread != lockBy) {
            wait();
        }
        isLock = true;
        lockCount++;
        lockBy = thread;
    }

    public synchronized void unlock() {
        lockCount--;
        if (lockBy == Thread.currentThread()) {
            if (lockCount == 0) {
                isLock = false;
                notify();
            }
        }

    }

}
