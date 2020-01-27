package per.hyc.designPattern.Mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * Java设计模式——行为模式——中介者模式(MEDIATOR)
 * https://blog.csdn.net/qq_32465815/article/details/80089326
 */
public class TestMediator2 {
    public static void main(String[] args) {
        // 实例化房屋中介
        Mediator mediator = new HouseMediator();

        Person landlordA, landlordB, renter;
        landlordA = new Landlord("房东1", mediator);
        landlordB = new Landlord("房东2", mediator);

        renter = new Renter("小小", mediator);

        // 房东注册中介
        mediator.registerLandlord(landlordA);
        mediator.registerLandlord(landlordB);
        // 求租者注册中介
        mediator.registerRenter(renter);

        // 求租者 发送求租消息
        renter.sendMessage("在南环附近租套房子，价格1000元左右一个月");
        System.out.println("--------------------------");
        // 房东A 发送房屋出租消息
        landlordA.sendMessage("南环附近有一房一厅出租  1200/月  光线好 近公交站");
    }

    /**
     * 抽象中介者类
     */
    static abstract class Mediator {
        // 用于添加储存 "房东"角色
        protected List<Person> landlordList = new ArrayList<Person>();

        // 用于添加储存 "求租者"角色
        protected List<Person> renterList = new ArrayList<Person>();

        /**
         * 中介者注册房东信息
         *
         * @param landlord 房东实体
         */
        public void registerLandlord(Person landlord) {
            landlordList.add(landlord);
        }

        /**
         * 中介者注册 求租者信息
         *
         * @param renter 求租者实体
         */
        public void registerRenter(Person renter) {
            renterList.add(renter);
        }

        /**
         * 声明抽象方法 由具体中介者子类实现 消息的中转和协调
         */
        public abstract void operation(Person person, String message);
    }

    /**
     * 抽象同事类：
     */
    static abstract class Person {
        // 维持一个抽象中介者的引用
        protected Mediator mediator;
        protected String name;

        public Person(String name, Mediator mediator) {
            this.mediator = mediator;
            this.name = name;
        }

        /**
         * 设置中介者对象
         *
         * @param mediator
         */
        public void setMediator(Mediator mediator) {
            this.mediator = mediator;
        }

        /**
         * 向中介 发送消息
         */
        protected abstract void sendMessage(String msg);

        /**
         * 从中介 获取消息
         */
        protected abstract void getMessage(String msg);
    }

    /**
     * 具体同事类：这里的角色是 房东
     */
    static class Landlord extends Person {
        public Landlord(String name, Mediator mediator) {
            super(name, mediator);
        }

        @Override
        protected void sendMessage(String msg) {
            mediator.operation(this, msg);
        }

        @Override
        protected void getMessage(String msg) {
            System.out.println("房东[" + name + "]收到中介发来的消息：" + msg);
        }
    }

    /**
     * 具体同事类：这里的角色是 租房者
     */
    static class Renter extends Person {
        public Renter(String name, Mediator mediator) {
            super(name, mediator);
        }

        @Override
        protected void sendMessage(String msg) {
            mediator.operation(this, msg);
        }

        @Override
        protected void getMessage(String msg) {
            System.out.println("求租者[" + name + "]收到中介发来的消息： " + msg);
        }
    }

    /**
     * 具体中介者类：这里的角色是 房屋出租中介
     */
    static class HouseMediator extends Mediator {
        @Override
        public void operation(Person person, String message) {
            if (person instanceof Renter) {
                // 将租屋的需求消息传递给 注册了的房东们
                for (Person landlord : landlordList) {
                    landlord.getMessage(message);
                }
            } else if (person instanceof Landlord) {
                // 将房东的出租房消息传递给 注册了的 房屋求租者们
                for (Person renter : renterList) {
                    renter.getMessage(message);
                }
            }
        }
    }
}
