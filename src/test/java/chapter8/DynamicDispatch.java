package chapter8;

/**
 * Created by zjb on 2019/7/19.
 */
public class DynamicDispatch {
    static abstract class Human {
        abstract void sayHello();
    }

    static class Man extends Human {
        @Override
        void sayHello() {
            System.out.println("hello,gentleman");
        }
    }

    static class Women extends Human {
        @Override
        void sayHello() {
            System.out.println("hello ,lady");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human women = new Women();
        man.sayHello();
        women.sayHello();
        man = new Women();
        man.sayHello();
    }
}
