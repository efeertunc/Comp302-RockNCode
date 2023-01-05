package domain.gameObjects.powerUps.bottle;


import domain.gameObjects.powerUps.PowerUp;

public class PlasticBottle extends PowerUp {
    private int numBottle=0;
    int id=0;

    @Override
    public void use() {
        //throw bottle

    }

    public int getNumBottle() {
        return numBottle;
    }

    @Override
    public void increment() {
        this.numBottle +=1;
    }
    @Override
    public void decrease(){
       this.numBottle-=1;
    }

}

