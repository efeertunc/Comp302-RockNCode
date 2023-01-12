package domain.gameObjects.powerUps;

public class HintPower extends PowerUp implements CollectablePowerUpI {

    private int id;

    private int numHint;

    public int getNumHint() {
        return numHint;
    }

    public void setNumHint(int numHint) {
        this.numHint = numHint;
    }

    @Override
    public void use() {

    }

    @Override
    public void increment() {
        numHint++;
    }

    @Override
    public void decrease() {
        numHint--;
    }

    @Override
    public int getNum() {
        return numHint;
    }
}
