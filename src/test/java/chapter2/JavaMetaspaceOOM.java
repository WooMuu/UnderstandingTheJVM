package chapter2;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by zjb on 2019/7/10.
 * VM Args: -XX:MetaspaceSize=10M -XX:MaxMetaspeceSize=10M
 * <p>
 * jdk8以后，字符串常量池已经放到了heap中
 * 因此测试Metaspace的OOM需要不断产生大量的类去填满Metaspace
 */
public class JavaMetaspaceOOM {

    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                    return proxy.invokeSuper(obj, args);
                }
            });
            enhancer.create();
        }

    }

    static class OOMObject {
    }
}
