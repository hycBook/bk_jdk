package per.hyc.designPattern.Factory;

import org.springframework.util.Assert;

/**
 * a.在客户端Client中可以将工厂模式的主要结构看着很清楚，首先我们要有IFactory这个工厂的父接口，所有的子类或者子接口都可以实现它。
 * AddFactory则是子类的代表之一，所以利用java的多态来实现，降低代码的耦合性。而同时每个子工厂中拥有每条生产独特产品的生产线。
 * 由此，工厂和产品挂上钩了，联系上了。每个子工厂生产出来的都是独特的产品。
 * <p>
 * b.比“简单工厂模式”的优缺点
 * <p>
 * 优点：克服了简单工厂违背开放-封闭原则的缺点，又保留了封装对象创建过程的优点,降低客户端和工厂的耦合性，
 * 所以说“工厂模式”是“简单工厂模式”的进一步抽象和推广。
 * <p>
 * 缺点：每增加一个产品，相应的也要增加一个子工厂，加大了额外的开发量。
 */
public class TestFactory {
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        ICar carAudi = new AudiFactory().createCar();
        carAudi.run();

        ICarFactory carFactory = null;
//        ICar carByd = new BydFactory().createCar();
        try {
            carFactory = (ICarFactory) Class.forName("per.hyc.designPattern.Factory.BydFactory").newInstance();
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
        Assert.notNull(carFactory, "carFactory不能为空");
        ICar carByd = carFactory.createCar();
        carByd.run();
    }
}
