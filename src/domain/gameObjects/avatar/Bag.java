package domain.gameObjects.avatar;

import domain.gameObjects.powerUps.HintPower;
import domain.gameObjects.powerUps.PowerUp;
import domain.gameObjects.powerUps.bottle.PlasticBottle;
import domain.gameObjects.powerUps.protectVest.ProtectionVest;

import java.util.ArrayList;

public class Bag {

    private PowerUp[] powerUpList = new PowerUp[3];

    public Bag() {
        powerUpList[0] = new PlasticBottle();
        powerUpList[1]= new ProtectionVest();
        powerUpList[2]= new HintPower();

    }

    public void addPowerUp(PowerUp powerUp) {
      powerUpList[powerUp.getID()].increment();

    }

    public void removePowerUp(PowerUp powerUp) {
        powerUpList[powerUp.getID()].decrease();
    }

    public boolean consistsBottle() {
        for (PowerUp powerUp : powerUpList) {
            if (powerUp instanceof PlasticBottle) {
                if (((PlasticBottle) powerUp).getNumBottle() > 0) {
                    return true;

                }
            }
        }
        return false;
    }

    public boolean consistsVest() {
        int a=((ProtectionVest) powerUpList[1]).getNumVest();
        if (a==0){
            return false;
        }
        return true;
    }

    public boolean consistsHint() {
        int a=((HintPower) powerUpList[1]).getNumHint();
        if (a==0){
            return false;
        }
        return true;
    }

    public void useHint() {
    }

    public PowerUp[] getPowerUpList() {
        return powerUpList;
    }


}
