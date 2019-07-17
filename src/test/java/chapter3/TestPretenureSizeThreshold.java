package chapter3;

/**
 * Created by zjb on 2019/7/11.
 * VM Args:
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 * -XX:PretenureSizeThreshold=1
 */
public class TestPretenureSizeThreshold {
    private static final int _1M = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation = new byte[4 * _1M];
    }

}
