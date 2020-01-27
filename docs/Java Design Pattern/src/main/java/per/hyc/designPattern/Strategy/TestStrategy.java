package per.hyc.designPattern.Strategy;

/**
 * 1、 多个类只区别在表现行为不同，可以使用Strategy模式，在运行时动态选择具体要执行的行为。
 * 2、 需要在不同情况下使用不同的策略(算法)，或者策略还可能在未来用其它方式来实现。
 * 3、 对客户隐藏具体策略(算法)的实现细节，彼此完全独立。
 */
public class TestStrategy {
    static void test() {
        Context context = new Context(new OperationAdd());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationSubstract());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationMultiply());
        System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
    }

    public static void main(String[] args) {
        test();
    }
}
