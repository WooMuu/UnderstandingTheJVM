package chapter4;

import java.util.Map;

/**
 * Created by zjb on 2019/7/12.
 */
public class ThreadTraceTest {
    public static void main(String[] args) {
        Map<Thread, StackTraceElement[]> threadMap = Thread.getAllStackTraces();
        for (Map.Entry<Thread, StackTraceElement[]> stackTrace : threadMap.entrySet()) {
            Thread thread = stackTrace.getKey();
            StackTraceElement[] stack = stackTrace.getValue();
            System.out.println("线程: " + thread.getName());
            for (StackTraceElement element : stack) {
                System.out.println(element);
            }
        }
    }
}
