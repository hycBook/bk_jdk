package per.hyc.designPattern.Builder;


/**
 * 定义：  建造者设计模式是一个构造复杂对象的设计模式，在一个软件系统中，可能会面临创建一个复杂对象的工作，
 * 如果使用单一的方法或者单一的对象来创建会比较烦琐，当所创建复杂对象发生改变时，整个系统就可能面临剧烈的变化。
 * 这时就需要我们将这个复杂对象的创建过程分解成若干部分，各个子部分用一定的算法构成。
 * 但是，子部分可能会经常发生变化，如何能保证整体创建工作的稳定性呢？
 * 这就需要建造者模式，建造者模式把复杂对象的创建与表示分离，使得同样的构建过程可以创建不同的表示。
 * <p>
 * 建造者模式的使用场景
 *     1》当生成的产品对象内部具有复杂的结构时；
 * <p>
 *     2》当复杂对象需要与表示分离，可能需要创建不同的表示时；
 * <p>
 *     3》当需要向客户隐藏产品内部结构的表现时。
 * <p>
 *     建造者模式适用于对象结构复杂、对象构造和表示分离的情况。任何一种软件设计模式的最终目标都是实现软件的高内聚、低耦合，实现易扩展、易维护。建造者设计模式的实质是解耦组装过程和创建具体部件，使得不用去关心每个部件是如何组装的，只需要知道各个部件是干什么的（实现什么功能）。建造者模式使得产品内部的结构表现可以独立变化，客户端不需要知道产品内部结构的组成细节。抽象工厂设计模式是解决“产品系列家族”的一种模式，而建造者设计模式是解决“一个对象部分”需要变化的一种设计模式。
 * <p>
 *     建造者设计模式的优点在于其构建和表示的分离，有效地将复杂对象处理过程分解，降低功能模块之间的耦合度，增强模块内部的内聚度，使得其在软件设计模式中具有极其重要的位置。
 */
public class TestBuilder {
    public static void main(String[] args) {
        test1();
        System.out.println();

        test2();
        System.out.println();

        test3();
    }

    private static void test1() {
        Builder builder = new ConcreteBuilder();
        Director director = new Director(builder);
        director.construct();
        Product product = builder.retrieveResult();
        System.out.println(product.getPart1());
        System.out.println(product.getPart2());
    }

    private static void test2() {
        // 套餐A
        // 准备套餐A的服务员
        MealBuilder mealBuilderA = new MealBuilderA();
        KFCWaiter waiter = new KFCWaiter(mealBuilderA);
        // 获得套餐
        Meal mealA = waiter.construct();
        System.out.print("套餐A的组成部分:");
        System.out.println("食物：" + mealA.getFood() + "；   " + "饮品：" + mealA.getDrink());

        // 套餐B
        // 准备套餐B的服务员
        MealBuilder mealBuilderB = new MealBuilderB();
        waiter = new KFCWaiter(mealBuilderB);
        // 获得套餐
        Meal mealB = waiter.construct();
        System.out.print("套餐B的组成部分:");
        System.out.println("食物：" + mealB.getFood() + "；   " + "饮品：" + mealB.getDrink());
    }

    private static void test3() {
        MealAndBuilder mealAndBuilder = new MealAndBuilder.MealBuilder().buildFood().buildDrink().getMeal();
        System.out.println(mealAndBuilder);
    }

}
