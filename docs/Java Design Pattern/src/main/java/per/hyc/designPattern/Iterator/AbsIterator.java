package per.hyc.designPattern.Iterator;

public interface AbsIterator {
    Object First();

    Object Next();

    boolean IsDone();

    Object CurrentItem();
}

/**
 * ConcreteIterator具体迭代器类，继承AbsIterator
 */
class ConcreteIterator implements AbsIterator {
    private ConcreteAggregate aggragate;
    private int current = 0;

    public ConcreteIterator(ConcreteAggregate aggragate) {
        this.aggragate = aggragate;
//        this.current = aggragate.Count()-1; // 逆序
    }

    @Override
    public Object First() { //得到聚集的第一个对象
        return aggragate.getItems(0);
    }

    @Override
    public Object Next() { //得到聚集的下一个对象
        current++; // 正序
//        current--; // 逆序
        if (current < aggragate.Count()) { // 正序
//        if (current >= 0) { // 逆序
            return aggragate.getItems(current);
        }
        return null;
    }

    @Override
    public boolean IsDone() { //判断遍历是否到结尾
        return current >= aggragate.Count(); // 正序
//        return current < 0; // 逆序
    }

    @Override
    public Object CurrentItem() { //返回遍历时的当前对象
        return aggragate.getItems(current);
    }
}