package per.hyc.designPattern.SimpleFactory;

public class OperationDiv implements IOperation {
    @Override
    public double GetResult(double _numberA, double _numberB) {
        double result = 0;
        if (_numberB == 0) {
            try {
                throw new Exception("除数不能为0.");
            } catch (Exception e) {
            }
        }
        result = _numberA / _numberB;
        return result;
    }
}