package chapter2;

import java.util.ArrayList;

/**
 * Created by zjb on 2019/7/10.
 * java堆用于存储对象实例，只要不断的创建对象，并且保证GC Roots到对象之间有可达路径
 * 来避免垃圾回收机制清除这个对象，那么在对象数量到达最大堆的容量限制后就会产生内存溢出异常。
 * <p>
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOoutOfMemoryError
 * -Xms 堆的最小值
 * -Xmx 堆的最大值
 * -XX:HeapDumpOnOutOfMemoryError  让虚拟机在出现内存溢出时dump出当前内存堆转储快照
 */
public class HeapOOM {
    static class OOMObject {

    }

    public static void main(String[] args) {
        ArrayList<OOMObject> list = new ArrayList<OOMObject>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
