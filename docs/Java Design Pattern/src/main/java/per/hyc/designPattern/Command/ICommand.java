package per.hyc.designPattern.Command;

/**
 * 抽象命令接口ICommand：定义命令的接口，声明执行的方法。
 */
public interface ICommand {
    void execute();
}

/**
 * 具体的命令对象ConcreteCommand：持有具体的接受者对象，完成具体的具体的命令。
 */
class ConcreteCommandA implements ICommand {
    private Receiver receiver;

    public ConcreteCommandA(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        System.out.println("ConcreteCommandA execute ...");
        receiver.execute();
    }
}

class ConcreteCommandB implements ICommand {
    private Receiver receiver;

    public ConcreteCommandB(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        System.out.println("ConcreteCommandB execute ...");
        receiver.execute();
    }
}