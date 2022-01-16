package per.hyc.designPattern.Facade;

import java.util.ArrayList;
import java.util.List;

public class ControllerFacade {
    private List<IEquipment> equipments;

    public ControllerFacade() {
        this.equipments = new ArrayList<>();
    }

    public void addEquipment(IEquipment equipment) {
        this.equipments.add(equipment);
    }

    public void addEquipments(List<IEquipment> equipments) {
        this.equipments.addAll(equipments);
    }


    public void on() {
        for (IEquipment equipment : equipments) {
            equipment.on();
        }
        System.out.println();
    }

    public void off() {
        for (IEquipment equipment : equipments) {
            equipment.off();
        }
        System.out.println();
    }
}
