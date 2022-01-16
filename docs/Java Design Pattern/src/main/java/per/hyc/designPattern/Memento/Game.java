package per.hyc.designPattern.Memento;

/**
 * 游戏
 */
public class Game {

    /**
     * 玩家走的步数
     */
    private int playerStep;

    /**
     * 备份游戏
     */
    public GameMemento createGameMemento() {
        return new GameMemento(playerStep);
    }

    /**
     * 开始玩游戏
     */
    public void play() {
        playerStep = 0;
    }

    /**
     * 恢复备份
     */
    public void restore(GameMemento gameMemento) {
        this.playerStep = gameMemento.getPlayerSteps();
    }

    public int getPlayerStep() {
        return playerStep;
    }

    public void setPlayerStep(int playerStep) {
        this.playerStep = playerStep;
    }
}
