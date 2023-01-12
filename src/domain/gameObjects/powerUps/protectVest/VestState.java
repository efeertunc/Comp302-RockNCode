package domain.gameObjects.powerUps.protectVest;

import domain.gameObjects.alien.AlienType;

public interface VestState {

    void takeDamage(AlienType type, int damage);
}
