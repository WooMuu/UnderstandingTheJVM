package chapter4;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by zjb on 2019/7/12.
 * VM Args:
 * -Xms100M -Xmx100M -XX:+UseSerialGC
 */
public class OOMObject {

    public byte[] paceholder = new byte[64 * 1204];//64kb

    public static void fillHeap(int num) throws InterruptedException {
        ArrayList<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            TimeUnit.MILLISECONDS.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
    }
}
