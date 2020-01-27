package per.hyc.designPattern.Mediator;

/**
 * 定义各个同事类公有的方法，并声明了一些抽象方法来供子类实现，
 * 同时它维持了一个对抽象中介者类的引用，其子类可以通过该引用来与中介者通信。
 */
public abstract class AbsColleague {
    public String name;
    public Mediator mediator;

    public AbsColleague(String name, Mediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }
}

/**
 * 每一个同事对象在需要和其他同事对象通信时，先与中介者通信，
 * 通过中介者来间接完成与其他同事类的通信；在具体同事类中实现了在抽象同事类中声明的抽象方法。
 */
class ColleagueA extends AbsColleague {
    /**
     * 具体同事类继承自Colleague,此刻就可以与中介者mediator进行通信了
     */
    public ColleagueA(String name, Mediator mediator) {
        super(name, mediator);
    }

    public void getMessage(String message) {
        System.out.println("同事A" + name + "获得信息" + message);
    }

    /**
     * 同事A与中介者通信
     */
    public void contact(String message) {
        mediator.contact(message, this);
    }
}

class ColleagueB extends AbsColleague {
    public ColleagueB(String name, Mediator mediator) {
        super(name, mediator);
    }

    public void getMessage(String message) {
        System.out.println("同事B" + name + "获得信息" + message);
    }

    /**
     * 同事B与中介者通信
     */
    public void contact(String message) {
        mediator.contact(message, this);
    }
}
