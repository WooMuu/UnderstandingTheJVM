package chapter8;

/**
 * Created by zjb on 2019/7/18.
 * 栈帧中局部变量表的slot有自己的作用域，当程序计数器的值超出某个变量作用域后，
 * 这个变量对应的slot就可以交给其他变量使用。
 */
public class LocalVariableTableSlot {
    public static void main(String[] args) {
        {
            byte[] bytes = new byte[1024 * 1024 * 64];
        }
        int i = 0;
        System.gc();
    }
}
