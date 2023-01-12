package domain.gameObjects.powerUps.protectVest;

import domain.building.BuildingTracker;
import domain.gameObjects.alien.AlienType;

public class HasVest implements VestState {

    @Override
    public void takeDamage(AlienType type, int damage) {
        //avatar does not take damage from shooter alien when state is HasNVest
        //avatar takes damage from blind alien when state is HasVest

        if (type == AlienType.BLIND) {
            BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar().takeDamage(damage);
        }
        if (type == AlienType.SHOOTER) {
            System.out.println("You are protected by your vest from shooter alien!");
        }

    }
}
