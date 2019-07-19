package chapter8;

/**
 * Created by zjb on 2019/7/19.
 */
public class StaticDispatch {

    static abstract class Human {
    }

    static class Man extends Human {
    }

    static class Women extends Human {
    }

    public void sayHello(Human guy) {
        System.out.println("hello,guy!");
    }

    public void sayHello(Man man) {
        System.out.println("hello,gentleman!");
    }

    public void sayHello(Women women) {
        System.out.println("hello,lady!");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human women = new Women();
        StaticDispatch sr = new StaticDispatch();
        sr.sayHello(man);
        sr.sayHello(women);

    }

}

