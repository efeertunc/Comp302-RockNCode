package domain.gameObjects.avatar;

import domain.gameObjects.powerUps.IPowerUp;
import domain.gameObjects.powerUps.bottle.PlasticBottle;

import java.util.ArrayList;

public class Bag {

    private IPowerUp[] bag = new IPowerUp[3];

    public Bag() {
    }

    public void addPowerUp(IPowerUp powerUp) {

    }

    public void removePowerUp(IPowerUp powerUp) {

    }

    public boolean consistsBottle() {
        for (IPowerUp powerUp : bag) {
            if (powerUp instanceof PlasticBottle) {
                return true;
            }
        }
        return false;
    }

    public boolean consistsVest() {
        return true;
    }

    public boolean consistsHint() {
        return true;
    }

    public void useHint() {
    }


}
