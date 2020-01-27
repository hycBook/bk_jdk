package per.hyc.designPattern.Builder;


public class MealAndBuilder {
    private String food;
    private String drink;

    /**
     * 私有化构造，不允许客户端直接new
     * @param mealBuilder
     */
    private MealAndBuilder(MealBuilder mealBuilder) {
        this.food = mealBuilder.food;
        this.drink = mealBuilder.drink;
    }

    public static class MealBuilder {
        private String food;  // 这里可以设置一些默认值
        private String drink; // 这里可以设置一些默认值

        public MealBuilder buildFood() {
            this.food = "薯条";
            return this;
        }

        public MealBuilder buildDrink() {
            this.drink = "可乐";
            return this;
        }

        public MealAndBuilder getMeal() {
            return new MealAndBuilder(this);
        }
    }

    @Override
    public String toString() {
        return "Meal{" +
                "food='" + food + '\'' +
                ", drink='" + drink + '\'' +
                '}';
    }
}
