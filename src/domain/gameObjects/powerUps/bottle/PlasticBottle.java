package domain.gameObjects.powerUps.bottle;


import domain.gameObjects.powerUps.PowerUp;

public class PlasticBottle extends PowerUp {
    private int numBottle=0;
    int id=0;

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public void use() {
        //throw bottle

    }

    public int getNumBottle() {
        return numBottle;
    }

    public void setNumBottle(int x) {
        this.numBottle = x;
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

