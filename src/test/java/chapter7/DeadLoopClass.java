package chapter7;

/**
 * Created by zjb on 2019/7/18.
 * 如果多线程同时加载一个类，那么只会有一个线程去执行这个类的<clinit>()方法
 */
public class DeadLoopClass {
    public static void main(String[] args) {
        Runnable script = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " start");
                DeadClass deadLoopClass = new DeadClass();
                System.out.println(Thread.currentThread() + " run over");
            }
        };
        new Thread(script).start();
        new Thread(script).start();

    }
}

class DeadClass {
    static {
        if (true) {
            System.out.println(Thread.currentThread() + "init DeadLoopClass");
            while (true) {
            }
        }
    }
}
