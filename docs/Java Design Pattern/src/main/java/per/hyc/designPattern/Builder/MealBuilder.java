package per.hyc.designPattern.Builder;

public abstract class MealBuilder {
    Meal meal = new Meal();

    public abstract void buildFood();

    public abstract void buildDrink();

    public Meal getMeal() {
        return meal;
    }
}


/**
 * 套餐A的建造者
 */
class MealBuilderA extends MealBuilder {
    @Override
    public void buildDrink() {
        meal.setDrink("可乐");
    }

    @Override
    public void buildFood() {
        meal.setFood("薯条");
    }

}


/**
 * 套餐B的建造者
 */
class MealBuilderB extends MealBuilder {
    @Override
    public void buildDrink() {
        meal.setDrink("柠檬果汁");
    }

    @Override
    public void buildFood() {
        meal.setFood("鸡翅");
    }
}
