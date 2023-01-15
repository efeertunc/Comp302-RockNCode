package domain.gameObjects.powerUps.bottle;


import domain.building.BuildingTracker;
import domain.gameObjects.avatar.Avatar;
import domain.gameObjects.powerUps.CollectablePowerUpI;
import domain.gameObjects.powerUps.PowerUp;
import domain.gameObjects.powerUps.PowerUpTypes;

public class PlasticBottle extends PowerUp implements CollectablePowerUpI {

    private int numBottle;

    public PlasticBottle() {
        this.numBottle = 0;
    }

    @Override
    public void use() {
        BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar().changeBottleState();
    }

    public void setNumBottle(int x) {
        this.numBottle = x;
    }


    @Override
    public void increment() {
        numBottle++;
    }

    @Override
    public void decrease() {
        numBottle--;
    }

    @Override
    public int getNum() {
        return numBottle;
    }

    @Override
    public void setNumToPowerUp(int num) {
        numBottle = num;
    }


}

