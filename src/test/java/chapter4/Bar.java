package chapter4;

/**
 * Created by zjb on 2019/7/12.
 * VM Args:
 * -XX:+UnlockDiagnosticVMOptions
 -XX:+DebugNonSafepoints
 -XX:+PrintAssembly
 -Xcomp
 -XX:CompileCommand=dontinline,*Bar.sum
 -XX:CompileCommand=compileonly,*Bar.sum

 如果是product版的HotSpot，需要加上一个-XX:+UnlockDiagnosticVMOptions
 由于会报错，增加-XX:+DebugNonSafepoints
参数 -XX:+PrintAssembly 输出反汇编内容
 两个 -XX:CompileCommand 意思是让编译器不要内联sum()并且只编译sum()
 其中参数 -Xcomp 是让虚拟机以编译模式执行代码，不需要执行足够次数来预热就嗯那个触发JIT编译
 */
public class Bar {
    int a = 1;
    static int b = 2;

    public int sum(int c) {
        return a + b + c;
    }

    public static void main(String[] args) {
        new Bar().sum(3);
    }
}
