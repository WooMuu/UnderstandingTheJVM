package chapter2;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by zjb on 2019/7/10.
 * 如果不设定直接内存，默认与堆内存最大值（-Xmx ）一样
 * <p>
 * 由于直接内存溢出时，heap dump文件中不会有明显异常，
 * 如果发现OOM后Dump文件很小，而程序中又又直接或间接使用了NIO，就可考虑是否是直接内存溢出导致
 * <p>
 * VM Args: -Xmx20M -XX:MaxDirectMemorySize=10M
 */
public class DirectMemoryOOM {
    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(100000L);
        }

    }
}
