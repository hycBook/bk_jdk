package per.hyc.designPattern.Composite;

import java.util.ArrayList;
import java.util.List;

public abstract class AbsCompany {
    protected String name;

    public AbsCompany(String name) {
        this.name = name;
    }

    abstract void add(AbsCompany company);

    abstract void remove(AbsCompany company);

    abstract void display(int depth);

    abstract void lineOfDuty();
}

class ConcreteCompany extends AbsCompany {
    private List<AbsCompany> children = new ArrayList<>();

    public ConcreteCompany(String name) {
        super(name);
    }

    @Override
    void add(AbsCompany company) {
        children.add(company);
    }

    @Override
    void remove(AbsCompany company) {
        children.remove(company);
    }

    @Override
    void display(int depth) {
        for (int ii = 0; ii < depth; ii++) {
            System.out.print("-");
        }
        System.out.println(name);

        for (AbsCompany company : children) {
            company.display(depth + 2);
        }
    }

    @Override
    void lineOfDuty() {
        for (AbsCompany company : children) {
            company.lineOfDuty();
        }
    }
}

class HRDepartment extends AbsCompany {
    public HRDepartment(String name) {
        super(name);
    }

    @Override
    void add(AbsCompany company) {

    }

    @Override
    void remove(AbsCompany company) {

    }

    @Override
    void display(int depth) {
        for (int ii = 0; ii < depth; ii++) {
            System.out.print("-");
        }
        System.out.println(name);
    }

    @Override
    void lineOfDuty() {
        System.out.println(name+" 员工招聘培训管理");
    }
}

class FinanceDepartment extends AbsCompany {
    public FinanceDepartment(String name) {
        super(name);
    }

    @Override
    void add(AbsCompany company) {

    }

    @Override
    void remove(AbsCompany company) {

    }

    @Override
    void display(int depth) {
        for (int ii = 0; ii < depth; ii++) {
            System.out.print("-");
        }
        System.out.println(name);
    }

    @Override
    void lineOfDuty() {
        System.out.println(name+" 公司财务收支管理");
    }
}
