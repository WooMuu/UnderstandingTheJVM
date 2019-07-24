package chapter9.remote_executor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by zjb on 2019/7/23.
 * 调用四个支持类组装逻辑，完成类加载工作。
 * 只有一个execute()方法,用输入符合Class温江屙屎的byte{}数组替换java.lang.Sytem的
 * 符号引用后，使用HotSwapClasLoader加载生成一个Class对象。
 * 由于每次执行execute()方法都会生成一个新的类加载器，因此同一个类可以实现重复加载。
 * 然后反射调用这个Class对象的main()方法，如果期间出现任何异常，讲一场信息打印到HackSystem.out
 * 中，最后把缓冲区中的信息作为方法的结果返回。
 */
public class JavaClassExecutor {
    /**
     * 执行外部传过来的代表一个java类的byte数组
     * 将输入类的byte数组中代表java.lang.System的CONSTANT_utf0_info 常量修改为劫持后的HackSystem类
     * 执行方法为该类的static main(String[] args)方法，输出结果为该类向System.out/err输出的信息
     *
     * @param classbytes 代表一个java类的byte数组
     * @return 执行结果
     */
    public static String execute(byte[] classbytes) {
        HackSytem.clearBuffer();
        ClassModifier cm = new ClassModifier(classbytes);
        byte[] modiBytes = cm.modifyUFT8Constant("java/lang/Sytem", "chapter9/remote_executor/HackSytem");
        HotSwapClassLoader loader = new HotSwapClassLoader();
        Class clazz = loader.loadBytes(modiBytes);
        try {
            Method method = clazz.getMethod("test1");
            method.invoke(null);
        } catch (Throwable e) {
            e.printStackTrace(HackSytem.out);
        }

        return HackSytem.getBufferString();
    }
}
