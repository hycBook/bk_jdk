package per.hyc.designPattern.Flyweight;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

interface ITicket {
    void showInfo(String bunk);
}

class TrainTicket implements ITicket {
    private String from;
    private String to;
    private int price;

    public TrainTicket(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void showInfo(String bunk) {
        this.price = new Random().nextInt(500);
        System.out.println(String.format("%s->%s：%s价格：%s 元", this.from, this.to, bunk, this.price));
    }
}

class TicketFactory {
    private static Map<String, ITicket> sTicketPool = new ConcurrentHashMap<>();

    public static ITicket queryTicket(String from, String to) {
        String key = from + "->" + to;
        if (TicketFactory.sTicketPool.containsKey(key)) {
            System.out.println("使用缓存 ==> " + key);
            return TicketFactory.sTicketPool.get(key);
        }
        System.out.println("第一次查询，创建对象 ==> " + key);
        ITicket ticket = new TrainTicket(from, to);
        TicketFactory.sTicketPool.put(key, ticket);
        return ticket;
    }
}