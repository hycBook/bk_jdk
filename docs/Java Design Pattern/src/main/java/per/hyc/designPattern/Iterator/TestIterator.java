package per.hyc.designPattern.Iterator;

/**
 * 迭代器模式应用的场景及意义
 * (1)访问一个聚合对象的内容而无需暴露它的内部表示
 * <p>
 * (2)支持对聚合对象的多种遍历
 * <p>
 * (3)为遍历不同的聚合结构提供一个统一的接口
 * <p>
 * 迭代器模式(Iterator)就是分离了集合对象的遍历行为，抽象出一个迭代器类来负责，这样不仅可以做到不暴露集合的内部结构，又可以让外部代码透明地访问内部的数据
 * <p>
 * 迭代器模式的优点有：
 * <p>
 * 简化了遍历方式，对于对象集合的遍历，还是比较麻烦的，对于数组或者有序列表，我们尚可以通过游标来取得，但用户需要在对集合了解很清楚的前提下，
 * 自行遍历对象，但是对于hash表来说，用户遍历起来就比较麻烦了。而引入了迭代器方法后，用户用起来就简单的多了。
 * 可以提供多种遍历方式，比如说对有序列表，我们可以根据需要提供正序遍历，倒序遍历两种迭代器，用户用起来只需要得到我们实现好的迭代器，就可以方便的对集合进行遍历了。
 * 封装性良好，用户只需要得到迭代器就可以遍历，而对于遍历算法则不用去关心。
 * 迭代器模式的缺点：
 * <p>
 * 对于比较简单的遍历（像数组或者有序列表），使用迭代器方式遍历较为繁琐，大家可能都有感觉，像ArrayList，我们宁可愿意使用for循环和get方法来遍历集合。
 */
public class TestIterator {
    static void test() {
        ConcreteAggregate aggregate = new ConcreteAggregate();
        aggregate.addItems("小鸟");
        aggregate.addItems("小菜");
        aggregate.addItems("行李");
        aggregate.addItems("老外");
        aggregate.addItems("司机");
        aggregate.addItems("售票员");

        AbsIterator iterator = new ConcreteIterator(aggregate);
        while (!iterator.IsDone()) {
            System.out.println(iterator.CurrentItem() + " 请买票");
            iterator.Next();
        }
    }

    public static void main(String[] args) {
        test();
    }
}
