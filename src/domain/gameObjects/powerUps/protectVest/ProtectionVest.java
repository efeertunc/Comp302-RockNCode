package domain.gameObjects.powerUps.protectVest;


import domain.building.BuildingTracker;
import domain.gameObjects.avatar.Avatar;
import domain.gameObjects.powerUps.CollectablePowerUpI;
import domain.gameObjects.powerUps.PowerUp;
import factory.PanelType;
import factory.ViewType;
import main.EscapeFromKoc;
import panels.RunPanel;

public class ProtectionVest extends PowerUp implements CollectablePowerUpI {
    private int id;
    private int numVest;

    @Override
    public void use() {
        BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar().changeVestState();
        BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar().setVestTime(20);
    }

    @Override
    public void increment() {
        numVest++;
    }

    @Override
    public void decrease() {
        numVest--;
    }

    @Override
    public int getNum() {
        return numVest;
    }
}
