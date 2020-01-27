package per.hyc.designPattern.Visitor;

public class TestVisitor {
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        // 构建报表
        BusinessReport report = new BusinessReport();
        System.out.println("=========== CEO看报表 ===========");
        report.showReport(new CEOIVisitor());
        System.out.println();

        System.out.println("=========== CTO看报表 ===========");
        report.showReport(new CTOIVisitor());
    }
}
