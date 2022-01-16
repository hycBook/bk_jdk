package per.hyc.designPattern.Visitor;

import java.util.Random;

/**
 * 员工基类
 */
public abstract class Staff {
    public String name;
    /**
     * 员工KPI
     */
    public int kpi;

    public Staff(String name) {
        this.name = name;
        kpi = new Random().nextInt(10);
    }

    /**
     * 核心方法，接受Visitor的访问
     */
    public abstract void accept(IVisitor visitor);
}

/**
 * 经理
 */
class Manager extends Staff {
    public Manager(String name) {
        super(name);
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * 一年做的产品数量
     */
    public int getProducts() {
        return new Random().nextInt(10);
    }
}

/**
 * 工程师
 */
class Engineer extends Staff {
    public Engineer(String name) {
        super(name);
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * 工程师一年的代码数量
     */
    public int getCodeLines() {
        return new Random().nextInt(10 * 10000);
    }
}