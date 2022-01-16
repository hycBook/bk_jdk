package per.hyc.designPattern.Decorator;

public abstract class Decorator implements IComponent {
    private IComponent component;

    public Decorator(IComponent component) {
        this.component = component;
    }

    @Override
    public void operation() {
        component.operation();
    }
}

class ConcreteDecoratorA extends Decorator {
    public ConcreteDecoratorA(IComponent component) {
        super(component);
    }

    public void methodA() {
        System.out.println("我是ConcreteDecoratorA，添加的新功能");
    }

    @Override
    public void operation() {
        methodA();
        super.operation();
        System.out.println("ConcreteDecoratorA的operation执行完毕");
    }
}

class ConcreteDecoratorB extends Decorator {
    public ConcreteDecoratorB(IComponent component) {
        super(component);
    }

    public void methodB() {
        System.out.println("我是ConcreteDecoratorB，添加的新功能");
    }

    @Override
    public void operation() {
        methodB();
        super.operation();
        System.out.println("ConcreteDecoratorB的operation执行完毕");
    }
}

