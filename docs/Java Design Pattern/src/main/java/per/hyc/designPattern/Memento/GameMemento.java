package per.hyc.designPattern.Memento;

/**
 * 备份
 */
public class GameMemento {
    /**
     * 步数
     */
    private int playerSteps;

    /**
     * 备份步数
     */
    public GameMemento(int playerSteps) {
        this.playerSteps = playerSteps;
    }

    public int getPlayerSteps() {
        return playerSteps;
    }

    public void setPlayerSteps(int playerSteps) {
        this.playerSteps = playerSteps;
    }
}