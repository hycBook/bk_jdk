package per.hyc.designPattern.Factory;

public interface ICarFactory {
    ICar createCar();
}

class AudiFactory implements ICarFactory {
    @Override
    public ICar createCar() {
        return new Audi();
    }
}

class BydFactory implements ICarFactory {
    @Override
    public ICar createCar() {
        return new Byd();
    }
}
