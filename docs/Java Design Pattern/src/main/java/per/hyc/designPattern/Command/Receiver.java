package per.hyc.designPattern.Command;

/**
 * 接受者对象Receiver：接受者对象，真正执行命令的对象
 */
public class Receiver {
    public void execute() {
        System.out.println("receiver execute ... ");
    }
}