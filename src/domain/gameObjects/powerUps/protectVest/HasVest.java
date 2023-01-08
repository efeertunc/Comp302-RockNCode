package domain.gameObjects.powerUps.protectVest;

public class HasVest implements VestState {

    @Override
    public void takeDamage() {
        //avatar does not take damage when state is HasVest
    }
}
