package domain.gameObjects.powerUps.bottle;


import domain.building.BuildingTracker;
import domain.gameObjects.powerUps.CollectablePowerUpI;
import domain.gameObjects.powerUps.PowerUp;

public class PlasticBottle extends PowerUp implements CollectablePowerUpI {

    private int id;
    private int numBottle;

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
}

