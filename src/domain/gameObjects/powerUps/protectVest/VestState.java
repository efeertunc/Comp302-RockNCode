package domain.gameObjects.powerUps.protectVest;

import domain.gameObjects.alien.Alien;
import domain.gameObjects.alien.AlienType;

public interface VestState {

    void takeDamage(Alien alien, int damage);
}
