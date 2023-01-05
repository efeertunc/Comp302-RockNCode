package domain.gameObjects.powerUps.protectVest;


import domain.gameObjects.powerUps.PowerUp;

public class ProtectionVest extends PowerUp {

    private int numVest=0;
    int id=1;

    @Override
    public void use() {

    }
    @Override
    public void increment() {
        this.numVest+=1;
    }

    @Override
    public void decrease() {
        this.numVest-=1;
    }

    public int getNumVest() {
        return numVest;
    }
}
