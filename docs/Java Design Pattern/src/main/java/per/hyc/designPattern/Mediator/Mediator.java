package per.hyc.designPattern.Mediator;

/**
 * 定义一个接口，该接口用于与各同事对象之间进行通信。
 */
public interface Mediator {
    void contact(String content, AbsColleague coll);
}

/**
 * 抽象中介者的子类，通过协调各个同事对象来实现协作行为，它维持了对各个同事对象的引用。
 */
class ConcreteMediator implements Mediator {
    ColleagueA collA;
    ColleagueB collB;

    @Override
    public void contact(String content, AbsColleague coll) {
        if (coll == collA) {
            collB.getMessage(content);
        } else {
            collA.getMessage(content);
        }
    }

    public ColleagueA getCollA() {
        return collA;
    }

    public void setCollA(ColleagueA collA) {
        this.collA = collA;
    }

    public ColleagueB getCollB() {
        return collB;
    }

    public void setCollB(ColleagueB collB) {
        this.collB = collB;
    }


}
