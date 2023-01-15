package domain.gameObjects.powerUps.protectVest;

import domain.building.BuildingTracker;
import domain.gameObjects.alien.Alien;
import domain.gameObjects.alien.AlienType;
import domain.gameObjects.alien.blind.BlindAlien;
import domain.gameObjects.alien.shooter.ShooterAlien;

public class HasVest implements VestState {

    @Override
    public void takeDamage(Alien alien, int damage) {
        //avatar does not take damage from shooter alien when state is HasNVest
        //avatar takes damage from blind alien when state is HasVest

        if (alien instanceof BlindAlien) {
            BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar().takeDamage(alien, damage);
        }
        if (alien instanceof ShooterAlien) {
            BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar().takeDamage(alien, 0);
            System.out.println("You are protected by your vest from shooter alien!");
        }

    }
}
