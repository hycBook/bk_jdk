package per.hyc.designPattern.Flyweight;

/**
 * https://www.jianshu.com/p/a2aa75939766
 * 面向对象技术可以很好地解决一些灵活性或可扩展性问题，但在很多情况下需要在系统中增加类和对象的个数。
 * 当对象数量太多时，将导致运行代价过高，带来性能下降等问题。享元模式 正是为解决这一类问题而诞生的。
 * https://www.jianshu.com/p/3fb0b559602b
 */
public class TestFlyweight {
    public static void main(String[] args) {
        test1();
        System.out.println();

        test2();
    }

    private static void test1() {
        IFlyweight flyweight1 = FlyweightFactory.getFlyweight("aa");
        IFlyweight flyweight2 = FlyweightFactory.getFlyweight("bb");
        IFlyweight flyweight3 = FlyweightFactory.getFlyweight("bb");

        flyweight1.operation("a");
        flyweight2.operation("b");
        flyweight3.operation("2b");
    }

    private static void test2() {
        ITicket ticket = TicketFactory.queryTicket("深圳北", "潮汕");
        ticket.showInfo("硬座");
        ticket = TicketFactory.queryTicket("深圳北", "潮汕");
        ticket.showInfo("软座");
        ticket = TicketFactory.queryTicket("深圳北", "潮汕");
        ticket.showInfo("硬卧");
    }
}
