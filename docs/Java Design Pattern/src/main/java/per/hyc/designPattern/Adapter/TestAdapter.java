package per.hyc.designPattern.Adapter;

/**
 * 优点：
 * <p>
 * 　　　　1：将目标类和适配者类解耦，通过引入一个适配器类来重用现有的适配者类，无需修改原有结构。
 * <p>
 * 　　　　2：增加了类的透明性和复用性，将具体的业务实现过程封装在适配者类中，对于客户端类而言是透明的，而且提高了适配者的复用性，同一适配者类可以在多个不同的系统中复用。
 * <p>
 * 　　　　3：灵活性和扩展性都非常好，通过使用配置文件，可以很方便的更换适配器，也可以在不修改原有代码的基础上 增加新的适配器，完全复合开闭原则。
 * <p>
 * 　　缺点：
 * <p>
 * 　　　　1：一次最多只能适配一个适配者类，不能同时适配多个适配者。
 * <p>
 * 　　　　2：适配者类不能为最终类，在C#中不能为sealed类
 * <p>
 * 　　　　3：目标抽象类只能为接口，不能为类，其使用有一定的局限性。
 */
public class TestAdapter {
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        AbsPlayer baDier = new Forwards("巴蒂尔");
        baDier.attack();

        AbsPlayer maiKld = new Guards("麦克格雷迪");
        maiKld.attack();

        AbsPlayer yaoMing = new Translator("姚明");
        yaoMing.attack();
        yaoMing.defense();
    }
}
