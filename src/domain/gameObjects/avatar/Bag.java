package domain.gameObjects.avatar;

import domain.gameObjects.powerUps.HintPower;
import domain.gameObjects.powerUps.PowerUp;
import domain.gameObjects.powerUps.bottle.PlasticBottle;
import domain.gameObjects.powerUps.protectVest.ProtectionVest;

import java.util.ArrayList;

public class Bag {

    private PowerUp[] bag = new PowerUp[3];

    public Bag() {
        bag[0] = new PlasticBottle();
        bag[1]= new ProtectionVest();
        bag[2]= new HintPower();

    }

    public void addPowerUp(PowerUp powerUp) {
      bag[powerUp.getID()].increment();

    }

    public void removePowerUp(PowerUp powerUp) {
        bag[powerUp.getID()].decrease();
    }

    public boolean consistsBottle() {
        for (PowerUp powerUp : bag) {
            if (powerUp instanceof PlasticBottle) {
                return true;
            }
        }
        return false;
    }

    public boolean consistsVest() {
        int a=((ProtectionVest) bag[1]).getNumVest();
        if (a==0){
            return false;
        }
        return true;
    }

    public boolean consistsHint() {
        int a=((HintPower) bag[1]).getNumHint();
        if (a==0){
            return false;
        }
        return true;
    }

    public void useHint() {
    }


}
