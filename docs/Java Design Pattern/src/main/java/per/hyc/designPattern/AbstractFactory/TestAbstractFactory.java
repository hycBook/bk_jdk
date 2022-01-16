package per.hyc.designPattern.AbstractFactory;

public class TestAbstractFactory {
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        AbsDriver driver = new BusinessDriver();
        try {
            AbsAudiCar audiCar = driver.createAudiCar("奥迪");
            audiCar.drive();

            AbsBenzCar benzCar = driver.createBenzCar("奔驰");
            benzCar.drive();

            AbsBmwCar bmwCar = driver.createBmwCar("宝马");
            bmwCar.drive();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
