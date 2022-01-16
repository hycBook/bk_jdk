package per.hyc.designPattern.Flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 抽象享元角色
 */
public interface IFlyweight {
    void operation(String extrinsicState);
}

/**
 * 具体享元角色
 */
class ConcreteFlyweight implements IFlyweight {
    private String intrinsicState;

    public ConcreteFlyweight(String intrinsicState) {
        this.intrinsicState = intrinsicState;
    }

    @Override
    public void operation(String extrinsicState) {
        System.out.println("Object address: " + System.identityHashCode(this));
        System.out.println("IntrinsicState: " + this.intrinsicState);
        System.out.println("ExtrinsicState: " + extrinsicState);
    }
}

/**
 * 享元工厂
 */
class FlyweightFactory {
    private static Map<String, IFlyweight> pool = new HashMap<>();

    /**
     * 因为内部状态具备不变性，因此作为缓存的键
     */
    public static IFlyweight getFlyweight(String intrinsicState) {
        if (!pool.containsKey(intrinsicState)) {
            IFlyweight flyweight = new ConcreteFlyweight(intrinsicState);
            pool.put(intrinsicState, flyweight);
        }
        return pool.get(intrinsicState);
    }
}