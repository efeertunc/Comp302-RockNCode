package domain.gameObjects.powerUps.protectVest;


import domain.building.BuildingTracker;
import domain.gameObjects.avatar.Avatar;
import domain.gameObjects.powerUps.PowerUp;
import factory.PanelType;
import factory.ViewType;
import main.EscapeFromKoc;
import panels.RunPanel;

public class ProtectionVest extends PowerUp {
    private int id;
    private int numVest;

    public ProtectionVest(int numVest) {
        id = 2;
        this.numVest = numVest;

    }

    @Override
    public int getID() {
        return this.id;
    }
    @Override
    public void use() {
        BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar().changeVestState();
        BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar().setVestTime(20);
    }

    @Override
    public void increment() {
        this.numVest+=1;
    }

    @Override
    public void decrease() {
        this.numVest-=1;
    }

    @Override
    public int getNum() {
        return this.numVest;
    }
}
