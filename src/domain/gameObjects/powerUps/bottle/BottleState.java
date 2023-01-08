package domain.gameObjects.powerUps.bottle;

import helperComponents.Direction;

public interface BottleState {

    void moveOrThrow(Direction.fourDir dir);
}
