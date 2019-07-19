package chapter7;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zjb on 2019/7/18.
 * 类加载器只用于实现类的加载动作。
 * 对于任意一个类，都需要由加载它的类加载器和这个类本身一同确立在java虚拟机中的唯一性。
 * 每一个类加载器，都拥有一个独立的类名称空间。
 * 通俗讲：比较两个类是否相等，只有在两个类是同一个类加载器加载的前提下才有意义，否则，
 * 即使两个类来源同一个Class文件，被同一个虚拟机加载，只要加载他们的类加载器不同，
 * 那这两个类必定不相等。
 * <p>
 * 这里说的相等，包括代表类的Class对象的equals()、isAssignableFrom()/isInstance()
 * 方法的返回结果，也包括使用instanceof关键字做对象所属关系判定等情况。
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws Exception {
        ClassLoader myClassLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }

                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object obj = myClassLoader.loadClass("chapter7.ClassLoaderTest");
        System.out.println(obj.getClass());
        System.out.println(obj instanceof ClassLoaderTest);
    }
}
