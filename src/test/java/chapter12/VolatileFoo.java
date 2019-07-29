package chapter12;

import java.util.concurrent.TimeUnit;

/**
 * Created by zjb on 2019/7/25.
 */
public class VolatileFoo {

    public static final int MAX = 5;

    public static volatile int int_value = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            int local_value = int_value;
            while (local_value < MAX) {
                if (local_value != int_value) {
                    System.out.printf("The int_valuie is update to [%d]\n", int_value);
                    local_value = int_value;
                }

            }
        }, "Reader").start();

        new Thread(() -> {
            int local_value = int_value;
            while (local_value < MAX) {
                System.out.printf("The int_valuie will be changed to [%d]\n", ++local_value);
                int_value = local_value;
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Updater").start();
    }
}
