package per.hyc.designPattern.Command;

/**
 * 传递命令对象Invoker：持有命令对象，要求命令对象执行请求。
 */
public class Invoker {
    private ICommand concreteCommandA, concreteCommandB;

    public Invoker(ICommand concreteCommandA, ICommand concreteCommandB) {
        this.concreteCommandA = concreteCommandA;
        this.concreteCommandB = concreteCommandB;
    }

    public void orderA() {
        concreteCommandA.execute();
    }

    public void orderB() {
        concreteCommandB.execute();
    }
}