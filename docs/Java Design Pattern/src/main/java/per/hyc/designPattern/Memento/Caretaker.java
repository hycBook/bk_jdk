package per.hyc.designPattern.Memento;

/**
 * 备份信息管理类
 */
public class Caretaker {
    /**
     * 备份
     */
    private GameMemento gameMemento;

    /**
     * 恢复备份
     */
    public GameMemento retrieveMemento() {
        return this.gameMemento;
    }

    /**
     * 保存备份
     */
    public void saveMemento(GameMemento gameMemento) {
        this.gameMemento = gameMemento;
    }
}