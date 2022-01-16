package per.hyc.designPattern.Template;

/**
 * 定义一个操作中算法的骨架，而将一些步骤延迟到子类中，
 * 模板方法使得子类可以不改变算法的结构即可重定义该算法的某些特定步骤。
 */

public class TestTemplate {
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        System.out.println("学生甲抄的试卷：");

        AbsTestPaper paperA = new TestPaperA();
        paperA.TestQuestion1();
        paperA.TestQuestion2();
        paperA.TestQuestion3();

        System.out.println("\n学生乙抄的试卷：");
        AbsTestPaper paperB = new TestPaperB();
        paperB.TestQuestion1();
        paperB.TestQuestion2();
        paperB.TestQuestion3();
    }
}
