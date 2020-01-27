package per.hyc.designPattern.Facade;

public interface IEquipment {
    void on();

    void off();
}

class Television implements IEquipment {
    @Override
    public void on() {
        System.out.println("打开电视");
    }

    @Override
    public void off() {
        System.out.println("关电视");
    }
}


class Aircondition implements IEquipment {
    @Override
    public void on() {
        System.out.println("打开空调");
    }

    @Override
    public void off() {
        System.out.println("关空调");
    }
}


class Light implements IEquipment {
    @Override
    public void on() {
        System.out.println("开灯");
    }

    @Override
    public void off() {
        System.out.println("关灯");
    }
}

