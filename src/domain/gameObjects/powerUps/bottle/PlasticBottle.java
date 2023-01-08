package domain.gameObjects.powerUps.bottle;


import domain.building.BuildingTracker;
import domain.gameObjects.powerUps.PowerUp;

public class PlasticBottle extends PowerUp {

    private int id;
    private int numBottle;


    public PlasticBottle(int numBottle) {
        id = 0;
        this.numBottle = numBottle;

    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public void use() {
        BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar().changeBottleState();
    }

    @Override
    public void increment() {
        this.numBottle +=1;
    }
    @Override
    public void decrease(){
       this.numBottle-=1;
    }

    @Override
    public int getNum() {
        return this.numBottle;
    }

}

