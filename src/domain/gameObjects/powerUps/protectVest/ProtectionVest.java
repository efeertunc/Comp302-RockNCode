package domain.gameObjects.powerUps.protectVest;


import domain.building.BuildingTracker;
import domain.gameObjects.avatar.Avatar;
import domain.gameObjects.powerUps.CollectablePowerUpI;
import domain.gameObjects.powerUps.PowerUp;
import domain.gameObjects.powerUps.PowerUpTypes;
import factory.PanelType;
import factory.ViewType;
import main.EscapeFromKoc;
import panels.RunPanel;

public class ProtectionVest extends PowerUp implements CollectablePowerUpI {
    private int numVest;

    public ProtectionVest() {
        this.numVest = 0;
    }

    @Override
    public void use() {
        BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar().changeVestState();
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

    @Override
    public void setNumToPowerUp(int num) {
        numVest = num;
    }
}
