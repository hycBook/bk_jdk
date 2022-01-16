package per.hyc.designPattern.Strategy;

public interface IStrategy {
    int doOperation(int num1, int num2);
}

class OperationAdd implements IStrategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}

class OperationMultiply implements IStrategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}

class OperationSubstract implements IStrategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 - num2;
    }
}