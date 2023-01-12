package domain.gameObjects.powerUps;

import domain.building.BuildingTracker;
import factory.PanelType;
import factory.ViewType;
import main.EscapeFromKoc;
import panels.RunPanel;
import panels.RunningMap;

public class HintPower extends PowerUp implements CollectablePowerUpI {

    private int id;

    private int numHint;

    public int getNumHint() {
        return numHint;
    }

    public void setNumHint(int numHint) {
        this.numHint = numHint;
    }

    @Override
    public void use() {
        System.out.println("Hint use Function");
        ((RunPanel) EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Run)).getRunningMap().setHintPowerUp(true);
        //((RunPanel) EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Run)).RunningMap.repaint();
    }

    @Override
    public void increment() {
        numHint++;
    }

    @Override
    public void decrease() {
        numHint--;
    }

    @Override
    public int getNum() {
        return numHint;
    }
}
