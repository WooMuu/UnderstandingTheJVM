package chapter7;

/**
 * Created by zjb on 2019/7/17.
 * VM Args:
 * -XX:+TraceClassLoading
 */
public class NotInitialization {
    public static void main(String[] args) {
//        System.out.println(SubClass.value);
//        SuperClass[] subClasses = new SuperClass[10];
        System.out.println(ConstClass.HELLOWORD);
    }
}
