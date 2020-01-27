package per.hyc.designPattern.Facade;

import java.util.ArrayList;
import java.util.List;

public class TestFacade {
    public static void main(String[] args) {
        test();
        System.out.println();

        test2();
    }

    private static void test2() {
        ControllerFacade controllerFacade = new ControllerFacade();
        // 第一种添加电器的方式
//        IEquipment light = new Light();
//        IEquipment tv = new Television();
//        IEquipment ad = new Aircondition();
//        controllerFacade.addEquipment(light);
//        controllerFacade.addEquipment(tv);
//        controllerFacade.addEquipment(ad);

        // 第二种添加电器的方式
        List<IEquipment> equipments = new ArrayList<>();
        equipments.add(new Light());
        equipments.add(new Television());
        equipments.add(new Aircondition());
        controllerFacade.addEquipments(equipments);

        controllerFacade.on();
        controllerFacade.off();
    }

    private static void test() {
        Light light = new Light();
        Television tv = new Television();
        Aircondition ad = new Aircondition();

        System.out.println("起床了");
        light.on();
        tv.on();
        ad.on();
        System.out.println();

        System.out.println("准备睡觉了");
        light.off();
        tv.off();
        ad.off();
        System.out.println();
        System.out.println("电器已全部关闭，可以安心睡觉了");
    }
}
