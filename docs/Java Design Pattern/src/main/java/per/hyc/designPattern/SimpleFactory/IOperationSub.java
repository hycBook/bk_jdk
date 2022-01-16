package per.hyc.designPattern.SimpleFactory;

public class IOperationSub implements IOperation {
    @Override
    public double GetResult(double _numberA, double _numberB) {
        double result = 0;
        result = _numberA - _numberB;
        return result;
    }
}