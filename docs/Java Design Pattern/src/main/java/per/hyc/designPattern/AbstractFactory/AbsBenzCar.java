package per.hyc.designPattern.AbstractFactory;

public abstract class AbsBenzCar {
    private String name;

    public abstract void drive();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class BenzSportCar extends AbsBenzCar {
    public BenzSportCar(String name) {
        this.setName(name);
    }

    @Override
    public void drive() {
        System.out.println(this.getName() + "----AbsBenzSportCar-----------------------");
    }
}

class BenzBusinessCar extends AbsBenzCar {
    public BenzBusinessCar(String name) {
        this.setName(name);
    }
    @Override
    public void drive() {
        System.out.println(this.getName() + "----AbsBenzBusinessCar-----------------------");
    }
}
