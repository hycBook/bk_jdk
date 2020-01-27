package per.hyc.designPattern.Composite;


/**
 * 透明组合模式 将公共接口封装到抽象根节点（Component）中，那么系统所有节点就具备一致行为，
 * 所以如果当系统绝大多数层次具备相同的公共行为时，采用 透明组合模式
 * 也许会更好（代价：为剩下少数层次节点引入不需要的方法）；
 * 而如果当系统各个层次差异性行为较多或者树节点层次相对稳定（健壮）时，采用 安全组合模式
 */
public class TestComposite {
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        AbsCompany root = new ConcreteCompany("北京总公司");
        root.add(new HRDepartment("总公司人力资源部"));
        root.add(new FinanceDepartment("总公司财务部"));

        AbsCompany hdCompany = new ConcreteCompany("上海华东分公司");
        hdCompany.add(new HRDepartment("华东分公司人力资源部"));
        hdCompany.add(new FinanceDepartment("华东分公司财务部"));
        root.add(hdCompany);

        AbsCompany njCompany = new ConcreteCompany("南京办事处");
        njCompany.add(new HRDepartment("南京办事处人力资源部"));
        njCompany.add(new FinanceDepartment("南京办事处财务部"));
        root.add(njCompany);

        AbsCompany hzCompany = new ConcreteCompany("杭州办事处");
        hzCompany.add(new HRDepartment("杭州办事处人力资源部"));
        hzCompany.add(new FinanceDepartment("杭州办事处财务部"));
        root.add(hzCompany);

        System.out.println("\n结构图: ");
        root.display(3);

        System.out.println("\n职责: ");
        root.lineOfDuty();
    }
}
