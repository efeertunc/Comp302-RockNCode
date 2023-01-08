package domain.gameObjects.powerUps;

import domain.building.Building;
import domain.building.BuildingTracker;

public class ExtraLife extends PowerUp {

    private int id;
    private int numExtraLife;

    @Override
    public void use() {
        if (BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar().getLife() < 5) {
            BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar().addLife(1);
        }
    }
    @Override
    public void increment() {}

    @Override
    public void decrease() {}

    @Override
    public int getNum() {
        return numExtraLife;
    }

    @Override
    public int getID() {
        return id;
    }


}
