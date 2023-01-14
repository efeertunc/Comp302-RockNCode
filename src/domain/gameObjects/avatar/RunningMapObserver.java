package domain.gameObjects.avatar;

import helperComponents.Position;

public interface RunningMapObserver {

    void notifyBottleIsThrown(Position position);

    void notifyAvatarTakesDamage();
}
