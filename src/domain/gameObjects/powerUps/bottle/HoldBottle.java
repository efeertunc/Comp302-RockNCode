package domain.gameObjects.powerUps.bottle;

import domain.building.BuildingTracker;
import helperComponents.Direction;
import main.EscapeFromKoc;

public class HoldBottle implements BottleState {

    @Override
    //player throws bottle
    public void moveOrThrow(Direction.fourDir dir) {
        BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar().throwBottle(dir);
    }
}
