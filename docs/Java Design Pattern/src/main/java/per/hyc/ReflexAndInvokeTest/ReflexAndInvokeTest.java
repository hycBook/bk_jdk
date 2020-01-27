package per.hyc.ReflexAndInvokeTest;

import java.lang.reflect.Method;
import java.util.Properties;
import org.joor.Reflect;

/**
 * 反射机制+工厂模式 学习
 */
public class
ReflexAndInvokeTest {
    /**
     * 配置文件路径
     */
    private static String pathname = ".\\src\\main\\resources\\fruit.properties";

    /**
     * 反射方法测试学习
     */
    static void reflexTest() {
        Properties pro = GetProperties.getPro(pathname);
        Fruit orange = FruitFactory.getInstance(pro.getProperty("orange"));
        if (orange != null) {
            orange.eat();
        }
        Fruit apple = FruitFactory.getInstance(pro.getProperty("apple"));
        if (apple != null) {
            apple.eat();
        }
        System.out.println();
    }

    /**
     * invoke方法测试学习
     */
    static void invokeTest() throws Exception {
        Properties pro = GetProperties.getPro(pathname);
        String fruitClass = pro.getProperty("orange");
        Fruit fruit = FruitFactory.getInstance(fruitClass);

        String[] mds = {"wash", "eat"};
        for (String md : mds) {
            Method setPriceMethod = Class.forName(fruitClass).getMethod(md);
            setPriceMethod.invoke(fruit);
        }

        Method setPriceMethod = Class.forName(fruitClass).getMethod("setPrice", Double.class);
        setPriceMethod.invoke(fruit, 12.6);
        System.out.println();
    }

    /**
     * 基于Joor的反射学习
     */
    private static void joorReflex() {
        Properties pro = GetProperties.getPro(pathname);
        String fruitClass = pro.getProperty("apple");
        Reflect reflect = Reflect.on(fruitClass).create();
        reflect.call("setPrice",25.4);

        // 为包装类建立一个代理
        Fruit fruitProxy = Reflect.on(fruitClass).create().as(Fruit.class);
        Reflect.on(fruitProxy).call("setPrice",13.2);

        Fruit fruit = FruitFactory.getInstance(fruitClass);
        Reflect.on(fruit).call("setPrice",17.6);
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        reflexTest();
        invokeTest();
        joorReflex();
    }
}
