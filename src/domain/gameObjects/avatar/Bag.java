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

    private final HashMap<String, PowerUp> bag;

    public Bag(){
        bag = new HashMap<String, PowerUp>();
        bag.put(PowerUpTypes.BOTTLE.toString(), PowerUpFactory.getInstance().createPowerUp(PowerUpTypes.BOTTLE));
        bag.put(PowerUpTypes.VEST.toString(), PowerUpFactory.getInstance().createPowerUp(PowerUpTypes.VEST));
        bag.put(PowerUpTypes.HINT.toString(), PowerUpFactory.getInstance().createPowerUp(PowerUpTypes.HINT));
    }


    public Bag(HashMap<String, PowerUp> bag) {
        this.bag = bag;
    }


    public void addPowerUp(PowerUpTypes powerUp) {
        ((CollectablePowerUpI) getPower(powerUp)).increment();
        System.out.println("Power up added to bag:" + powerUp);
        BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar().notifyAvatarObserver();
    }


    public void decreasePowerUp(PowerUpTypes powerUp) {
        ((CollectablePowerUpI) getPower(powerUp)).decrease();
        BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar().notifyAvatarObserver();
    }


    public void setNumToPowerUp(PowerUpTypes powerUp, int num) {
        ((CollectablePowerUpI) getPower(powerUp)).setNumToPowerUp(num);
        System.out.println("Number of power up set to bag:" + powerUp);
    }


    public boolean consistsOf(PowerUpTypes powerUp){
        return ((CollectablePowerUpI) getPower(powerUp)).getNum() > 0;
    }


    public PowerUp getPower(PowerUpTypes powerUp){
        String powerUpStr = powerUp.toString();
        return bag.get(powerUpStr);
    }

    public int getPowerNum(PowerUpTypes powerUp){
        return ((CollectablePowerUpI) getPower(powerUp)).getNum();
    }

    public void usePowerUp(PowerUpTypes powerUp) {
        if (consistsOf(powerUp)) {
            getPower(powerUp).use();
            //decreasePowerUp(powerUp);
        }
    }

    public HashMap<String, PowerUp> getPowers() {
        return bag;
    }



}
