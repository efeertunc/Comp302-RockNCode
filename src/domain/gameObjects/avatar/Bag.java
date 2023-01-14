package domain.gameObjects.avatar;

import domain.building.BuildingTracker;
import domain.gameObjects.powerUps.CollectablePowerUpI;
import domain.gameObjects.powerUps.HintPower;
import domain.gameObjects.powerUps.PowerUp;
import domain.gameObjects.powerUps.PowerUpTypes;
import domain.gameObjects.powerUps.bottle.PlasticBottle;
import domain.gameObjects.powerUps.protectVest.ProtectionVest;
import factory.PowerUpFactory;

import java.util.HashMap;

public class Bag  {

    private final HashMap<PowerUpTypes, PowerUp> bag;

    public Bag(){
        bag = new HashMap<PowerUpTypes, PowerUp>();
        bag.put(PowerUpTypes.BOTTLE, PowerUpFactory.getInstance().createPowerUp(PowerUpTypes.BOTTLE));
        bag.put(PowerUpTypes.VEST, PowerUpFactory.getInstance().createPowerUp(PowerUpTypes.VEST));
        bag.put(PowerUpTypes.HINT, PowerUpFactory.getInstance().createPowerUp(PowerUpTypes.HINT));
    }


    public Bag(HashMap<PowerUpTypes, PowerUp> bag) {
        this.bag = bag;
    }


    public void addPowerUp(PowerUpTypes powerUp) {
        ((CollectablePowerUpI) bag.get(powerUp)).increment();
        System.out.println("Power up added to bag:" + powerUp);
    }


    public void decreasePowerUp(PowerUpTypes powerUp) {
        ((CollectablePowerUpI) bag.get(powerUp)).decrease();
    }


    public boolean consistsOf(PowerUpTypes powerUp){
        return ((CollectablePowerUpI) bag.get(powerUp)).getNum() > 0;
    }


    public PowerUp getPower(PowerUpTypes powerUp){
        return bag.get(powerUp);
    }

    public void usePowerUp(PowerUpTypes powerUp) {
        if (consistsOf(powerUp)) {
            bag.get(powerUp).use();
            //decreasePowerUp(powerUp);
        }
    }

    public HashMap<PowerUpTypes, PowerUp> getPowers() {
        return bag;
    }



}
