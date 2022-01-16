package per.hyc.designPattern.Factory;

public interface ICar {
    void run();
}
class Byd implements ICar {
    @Override
    public void run() {
        System.out.println("比亚迪再跑！");
    }
}

class Audi implements ICar {
    @Override
    public void run() {
        System.out.println("奥迪再跑！");
    }
}
