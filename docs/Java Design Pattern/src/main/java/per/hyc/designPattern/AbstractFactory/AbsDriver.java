package per.hyc.designPattern.AbstractFactory;

public abstract class AbsDriver {
    public abstract AbsBenzCar createBenzCar(String car) throws Exception;

    public abstract AbsBmwCar createBmwCar(String car) throws Exception;

    public abstract AbsAudiCar createAudiCar(String car) throws Exception;
}

class SportDriver extends AbsDriver {
    @Override
    public AbsBenzCar createBenzCar(String name) throws Exception {
        return new BenzSportCar(name);
    }

    @Override
    public AbsBmwCar createBmwCar(String name) throws Exception {
        return new BmwSportCar(name);
    }

    @Override
    public AbsAudiCar createAudiCar(String name) throws Exception {
        return new AudiSportCar(name);
    }
}

class BusinessDriver extends AbsDriver {
    @Override
    public AbsBenzCar createBenzCar(String name) throws Exception {
        return new BenzBusinessCar(name);
    }

    @Override
    public AbsBmwCar createBmwCar(String name) throws Exception {
        return new BmwBusinessCar(name);
    }

    @Override
    public AbsAudiCar createAudiCar(String name) throws Exception {
        return new AudiBusinessCar(name);
    }
}
