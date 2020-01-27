package per.hyc.designPattern.Strategy;

public class Context {
    private IStrategy strategy;

    Context(IStrategy strategy) {
        this.strategy = strategy;
    }

    int executeStrategy(int num1, int num2) {
        return strategy.doOperation(num1, num2);
    }
}

