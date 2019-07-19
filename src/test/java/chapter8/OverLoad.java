package chapter8;

import java.io.Serializable;

/**
 * Created by zjb on 2019/7/19.
 */
public class OverLoad {

//    public static void sayHello(Object arg) {
//        System.out.println("hello obj");
//    }

//    public static void sayHello(int arg) {
//        System.out.println("hello int");
//    }

//    public static void sayHello(long arg) {
//        System.out.println("hello long");
//    }

//    public static void sayHello(char arg) {
//        System.out.println("hello char");
//    }

//    public static void sayHello(Character arg) {
//        System.out.println("hello character");
//    }

    public static void sayHello(char... arg) {
        System.out.println("hello char...");
    }

//    public void sayHello(Serializable arg) {
//        System.out.println("hello serializable");
//    }

    public static void main(String... agrs) {
        sayHello('a');
    }
}
