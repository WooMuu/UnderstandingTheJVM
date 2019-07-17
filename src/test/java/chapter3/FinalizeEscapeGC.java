package chapter3;

import org.junit.Test;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by zjb on 2019/7/10.
 * 即使可达分析算法中不可达的对象，也并不是非死不可，
 * 真正宣告一个对象死亡，需要两次标记
 * 第一次标记：
 * 如果对象可达性分析后发现没有与GC Roots相连接的引用链，会第一次标记，
 * 并进行一次筛选，筛选条件是此对象是否有必要执行finalize()方法，
 * 当对象没有覆盖finalize()方法，或者finalize方法已经被虚拟机调用过，
 * 虚拟机认为这两种情况没必要执行。
 * 如果这个对象被判定为有必要执行finalize()方法，那么这个对象将会被放到一个叫F-Queue的队列中
 * 并在稍后一个由虚拟机自动建立的、优先级低的Finalizer线程中去执行它。
 * 这里所谓的执行时虚拟机会触发这个方法，但不保证会等待它执行结束
 * 原因是，若果一个对象finalize()执行缓慢，或者发生了死循环，很可能导致F-Queue中其他对象永久处于等待
 * 甚至导致整个内存回收系统崩溃，
 * 第二次标记：
 * 稍后GC将对F-Queue中的对象第二次标记，如果这时对象还没逃脱，基本上就会被回收
 */
public class FinalizeEscapeGC {
    @Test
    public void test() {
        List<GarbageCollectorMXBean> l = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean b : l) {
            System.out.println(b.getName());
        }
        System.gc();
    }

    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("yes ,i am still alive:)");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method is executed!");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();

        //对象第一次成功拯救自己
        SAVE_HOOK = null;
        System.gc();
        //因为finalize方法优先级很低，所以暂停0.5秒等待它
        TimeUnit.MILLISECONDS.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no im dead:(");
        }

        //下面代码完全相同，但自救却失败了
        SAVE_HOOK = null;
        System.gc();
        //因为finalize方法优先级很低，所以暂停0.5秒等待它
        TimeUnit.MILLISECONDS.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no im dead:(");
        }
    }
}
