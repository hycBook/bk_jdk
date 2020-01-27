package per.hyc.designPattern.AbstractFactory;

public abstract class AbsAudiCar {
    private String name;

    public abstract void drive();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class AudiSportCar extends AbsAudiCar {
    public AudiSportCar(String name) {
        this.setName(name);
    }

    @Override
    public void drive() {
        System.out.println(this.getName() + "----AbsAudiSportCar-----------------------");
    }
}

class AudiBusinessCar extends AbsAudiCar {
    public AudiBusinessCar(String name) {
        this.setName(name);
    }
    @Override
    public void drive() {
        System.out.println(this.getName() + "----AbsAudiBusinessCar-----------------------");
    }
}