package domain.gameObjects.powerUps;

public class HintPower extends PowerUp {

    private int numHint=0;

    public int getNumHint() {
        return numHint;
    }

    public void setNumHint(int numHint) {
        this.numHint = numHint;
    }

    int id= 2;

    @Override
    public void use() {

    }
    @Override
    public void increment() {
        this.numHint+=1;
    }
    @Override
    public void decrease() {
        this.numHint-=1;
    }
}
