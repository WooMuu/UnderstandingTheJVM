package chapter2;

/**
 * Created by zjb on 2019/7/10.
 * 操作系统分配给每个进程的内存有限，32位操作系统限制为2GB
 * 虚拟机提供了参数来控制java堆和方法区的这两部分内存的最大值，
 * 剩余的内存为    2GB(操作系统限制) - Xmx(最大堆容量) - MaxPermSize(最大方法去容量)
 * 程序计数器内存很小忽略不计，如果虚拟机进程本身耗费的内存不计算在内，
 * 剩下的内存就由虚拟机栈和本地方法栈瓜分
 * 每个线程分配到的内栈容量越大，可以建立的线程就越少，越容易把剩下内存耗尽。
 * <p>
 * 因此，建立过多线程导致内存溢出时，在不能减少线程和更换64位虚拟机的情况下，
 * 只能减少最大堆内存和栈容量来换取更多的线程
 * VM Args:-Xss2M
 */
public class JavaVMStackOOM {
    private void dontStop() {
        while (true) {
        }
    }

    public void stackLeakByThread() {
        while (true) {
            new Thread(() -> {
                dontStop();
            }).start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }

}
