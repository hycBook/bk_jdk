package per.hyc.designPattern.Template;

/**
 * 抽象父类（AbstractClass）：实现了模板方法，定义了算法的骨架。
 */
public abstract class AbsTestPaper {
    public abstract String Answer1();

    public abstract String Answer2();

    public abstract String Answer3();

    public void TestQuestion1() {
        System.out.println("第1题");
        System.out.println("答案" + Answer1());
    }

    public void TestQuestion2() {
        System.out.println("第2题");
        System.out.println("答案" + Answer2());
    }

    public void TestQuestion3() {
        System.out.println("第3题");
        System.out.println("答案" + Answer3());
    }
}

/**
 * 具体类（ConcreteClass)：实现抽象类中的抽象方法，即不同的对象的具体实现细节。
 * 学生甲抄的试卷
 */
class TestPaperA extends AbsTestPaper {
    @Override
    public String Answer1() {
        return "b";
    }

    @Override
    public String Answer2() {
        return "c";
    }

    @Override
    public String Answer3() {
        return "d";
    }
}


/**
 * 学生乙抄的试卷
 */
class TestPaperB extends AbsTestPaper {
    @Override
    public String Answer1() {
        return "c";
    }

    @Override
    public String Answer2() {
        return "b";
    }

    @Override
    public String Answer3() {
        return "a";
    }
}
