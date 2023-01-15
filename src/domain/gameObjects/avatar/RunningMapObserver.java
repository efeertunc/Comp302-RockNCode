package domain.gameObjects.avatar;

import domain.gameObjects.alien.Alien;
import helperComponents.Position;

public interface RunningMapObserver {

    void notifyBottleIsThrown(Position avatarPos, Position bottlePos);

    void notifyAvatarTakesDamage(Avatar avatar, Alien alien);
}
