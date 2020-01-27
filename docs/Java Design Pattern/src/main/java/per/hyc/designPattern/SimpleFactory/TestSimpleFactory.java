package per.hyc.designPattern.SimpleFactory;

/**
 * 模块清晰化，每个部分都各司其职，分工明确，代码就实现最渐层意义上的“可维护”啦。
 * 说到缺点，当我们需要增加一产品，比如在计算机中加入一个新的功能，可以求M的N次方，这样个小功能我们就要去添加一个新的类，
 * 同时我们需要在Factory中改动到switch里面的代码，这是耦合性很高的表现啦，所以我们就有了“工厂模式”的出现啦。
 */
public class TestSimpleFactory {

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        IOperation oper;

        String operate = "-";
        oper = createOperate(operate);
        double result = oper.GetResult(11.2, 2.2);
        System.out.println(result);

        operate = "+";
        oper = createOperate(operate);
        result = oper.GetResult(11.2, 2.2);
        System.out.println(result);
    }

    private static IOperation createOperate(String operate) {
        IOperation oper = null;
        switch (operate) {
            case "+":
                oper = new IOperationAdd();
                break;
            case "-":
                oper = new IOperationSub();
                break;
            case "*":
                oper = new OperationMul();
                break;
            case "/":
                oper = new OperationDiv();
                break;
            default:
                break;
        }
        return oper;
    }
}
