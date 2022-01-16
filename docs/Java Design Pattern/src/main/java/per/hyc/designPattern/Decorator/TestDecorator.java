package per.hyc.designPattern.Decorator;

/**
 * 适用场景：
 * <p>
 * 扩展一个类的功能。
 * 动态增加功能，动态撤销。
 * 优缺点：
 * <p>
 * 优点：
 * <p>
 * 装饰类和被装饰类可以独立发展，不会相互耦合
 * 动态的将责任附加到对象身上。
 * 缺点：
 * <p>
 * 多层装饰比较复杂。
 * <p>
 * https://www.cnblogs.com/shenbo-/p/9074032.html
 */
public class TestDecorator {
    static void test() {
        IComponent component = new ConcreteComponent();
        IComponent decoratorA = new ConcreteDecoratorA(component);
        ConcreteDecoratorB decoratorB = new ConcreteDecoratorB(decoratorA);
        decoratorB.operation();
    }

    public static void main(String[] args) {
        test();
    }
}
