package domain.gameObjects.powerUps.bottle;

import domain.building.BuildingTracker;
import helperComponents.Direction;

public class HoldNothing implements BottleState {

    /**
     * player moves in direction
     * @param dir direction of movement
     */
    @Override
    public void moveOrThrow(Direction.fourDir dir) {
        BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar().move(dir);
    }
}
