package chapter13.lock.un_rentrantLock;

/**
 * Created by zjb on 2019/7/29.
 */
public class UnReentrantLock {
    private boolean isLocked = false;

    public synchronized void lock() throws InterruptedException {
        while (isLocked) {
            wait();
        }
        this.isLocked = true;
    }

    public synchronized void unlock() {
        isLocked = false;
        notify();
    }
}
