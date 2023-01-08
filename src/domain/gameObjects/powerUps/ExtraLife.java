package domain.gameObjects.powerUps;

import domain.building.Building;
import domain.building.BuildingTracker;

public class ExtraLife extends PowerUp {

    @Override
    public void use() {
        if (BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar().getLife() < 5) {
            BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar().addLife(1);
        }
    }
    @Override
    public void increment() {

    }
    @Override
    public int getID() {
        return 0;
    }
    @Override
    public void decrease() {

    }
}
