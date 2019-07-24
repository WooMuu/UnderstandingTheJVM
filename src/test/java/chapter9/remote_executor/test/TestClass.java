package chapter9.remote_executor.test;

import chapter9.remote_executor.JavaClassExecutor;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zjb on 2019/7/23.
 */
public class TestClass {
    public static void main(String[] args) {
        System.out.println("hahjah");
    }

    public static void test1(){
        System.out.println("hhh");
    }

    @Test
    public void test() throws IOException {
        FileInputStream is = new FileInputStream("E:\\demos\\UnderstandingTheJVM\\target\\test-classes\\chapter9\\remote_executor\\test\\TestClass.class");
        byte[] b = new byte[is.available()];
        is.read(b);
        is.close();
        String execute = JavaClassExecutor.execute(b);
        System.out.println(execute);

    }
}
