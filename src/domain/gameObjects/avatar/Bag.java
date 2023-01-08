package domain.gameObjects.avatar;

import domain.building.BuildingTracker;
import domain.gameObjects.powerUps.HintPower;
import domain.gameObjects.powerUps.PowerUp;
import domain.gameObjects.powerUps.PowerUpTypes;
import domain.gameObjects.powerUps.bottle.PlasticBottle;
import domain.gameObjects.powerUps.protectVest.ProtectionVest;

import java.util.HashMap;

public class Bag {

    private final HashMap<PowerUpTypes, PowerUp> bag;

    public Bag(){
        bag = new HashMap<PowerUpTypes, PowerUp>();
        bag.put(PowerUpTypes.BOTTLE, new PlasticBottle(0));
        bag.put(PowerUpTypes.VEST, new ProtectionVest(0));
        bag.put(PowerUpTypes.HINT, new HintPower(0));
    }


    public Bag(HashMap<PowerUpTypes, PowerUp> bag) {
        this.bag = bag;
    }


    public void addPowerUp(PowerUpTypes powerUp) {
        bag.get(powerUp).increment();
    }


    public void decreasePowerUp(PowerUpTypes powerUp) {
        bag.get(powerUp).decrease();
    }


    public boolean consistsOf(PowerUpTypes powerUp){
        return bag.get(powerUp).getNum() > 0;
    }


    public PowerUp getPower(PowerUpTypes powerUp){
        return bag.get(powerUp);
    }

    public void usePowerUp(PowerUpTypes powerUp) {
        if (consistsOf(powerUp)) {
            bag.get(powerUp).use();
            decreasePowerUp(powerUp);

        }
    }

    public HashMap<PowerUpTypes, PowerUp> getPowers() {
        return bag;
    }



}
