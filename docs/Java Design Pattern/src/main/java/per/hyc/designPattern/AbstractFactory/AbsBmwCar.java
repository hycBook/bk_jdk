package per.hyc.designPattern.AbstractFactory;

public abstract class AbsBmwCar {
    private String name;

    public abstract void drive();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class BmwSportCar extends AbsBmwCar {
    public BmwSportCar(String name) {
        this.setName(name);
    }
    @Override
    public void drive() {
        System.out.println(this.getName() + "----AbsBmwSportCar-----------------------");
    }
}

class BmwBusinessCar extends AbsBmwCar {
    public BmwBusinessCar(String name) {
        this.setName(name);
    }
    @Override
    public void drive() {
        System.out.println(this.getName() + "----AbsBmwBusinessCar-----------------------");
    }
}