package chapter2;

import java.util.ArrayList;

/**
 * Created by zjb on 2019/7/10.
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 *
 * JDK8以后，字符串常量放到了Heap中，因此本测试最终会导致heap的OOM
 * 可以尝试调节heap大小来更快获取结果
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
//        String str1 = new StringBuilder("计算机").append("软件").toString();
//        System.out.println(str1.intern() == str1);
//
//        String str2 = new StringBuffer("ja").append("va").toString();
//        System.out.println(str2.intern() == str2);

        //使用list保持常量池引用，避免full GC回收常量池行为
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
