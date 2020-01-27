package per.hyc.designPattern.Command;

public class TestCommand {
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        Receiver receiver = new Receiver();
        Invoker invoker = new Invoker(new ConcreteCommandA(receiver), new ConcreteCommandB(receiver));
        invoker.orderA();
        System.out.println();
        invoker.orderB();
    }
}
