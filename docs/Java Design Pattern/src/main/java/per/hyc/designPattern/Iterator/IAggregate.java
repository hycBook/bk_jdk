package per.hyc.designPattern.Iterator;

import java.util.ArrayList;
import java.util.List;

public interface IAggregate {
    AbsIterator CreateIterator();
}

/**
 * ConcreteAggregate具体聚集类，继承Aggregate
 */
class ConcreteAggregate implements IAggregate {
    private List<Object> items = new ArrayList<>();

    @Override
    public AbsIterator CreateIterator() {
        return new ConcreteIterator(this);
    }

    public int Count() {
        // 返回聚集总个数
        return items.size();
    }

    public Object getItems(int index) {
        return items.get(index);
    }

    public void addItems(Object item) {
        items.add(item);
    }
}
