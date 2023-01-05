package domain.gameObjects.powerUps;

import domain.building.Building;
import domain.building.BuildingTracker;

public class ExtraLife implements IPowerUp {

    @Override
    public void use() {
        if (BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar().getLife() < 5) {
            BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar().addLife(1);
        }
    }
}
