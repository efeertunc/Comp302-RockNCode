package domain.gameObjects.powerUps;

import domain.building.BuildingTracker;
import factory.PanelType;
import factory.ViewType;
import main.EscapeFromKoc;
import panels.RunPanel;
import panels.RunningMap;

public class HintPower extends PowerUp implements CollectablePowerUpI {

    private int numHint;

    public HintPower() {
        this.numHint = 0;
    }

    @Override
    public void use() {
        System.out.println("Hint use Function");
        BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar().setHintTime(10, true);
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
