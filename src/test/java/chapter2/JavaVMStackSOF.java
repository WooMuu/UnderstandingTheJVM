package chapter2;

/**
 * Created by zjb on 2019/7/10.
 * HotSpot虽然有-Xoss参数（设置本地方法栈大小），但无效
 * 栈容量只由-Xss设定
 * VM Args:-Xss128k
 * <p>
 * 使用 -Xss减少栈内存容量，结果抛出StackOverFlowError
 * 定义大量本地变量，增大此方法帧中本地变量表的长度，结果抛出StackOverFlowError
 */
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }

}
