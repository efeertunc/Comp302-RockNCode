package domain.gameObjects.powerUps.bottle;

import domain.gameObjects.powerUps.IPowerUp;

public class PlasticBottle implements IPowerUp {
    private int numBottle;

    @Override
    public void use() {
        //throw bottle
    }

    public int getNumBottle() {
        return numBottle;
    }

    public void setNumBottle(int numBottle) {
        this.numBottle = numBottle;
    }


}

