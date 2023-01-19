package domain.gameObjects.powerUps.protectVest;

import domain.building.BuildingTracker;
import domain.gameObjects.alien.Alien;
import domain.gameObjects.alien.AlienType;
import domain.gameObjects.avatar.Bag;

public class HasNoVest implements VestState {

    @Override
    public void takeDamage(Alien alien, int damage) {
        BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar().takeDamage(alien, damage);
        BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar().damageTakenFeedback();
    }
}
