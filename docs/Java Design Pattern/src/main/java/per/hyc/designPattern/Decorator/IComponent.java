package per.hyc.designPattern.Decorator;

public interface IComponent {
    void operation();
}


class ConcreteComponent implements IComponent {
    @Override
    public void operation() {
        System.out.println("我是ConcreteComponent，是最原始的实现类，我处于最底层");
    }
}
