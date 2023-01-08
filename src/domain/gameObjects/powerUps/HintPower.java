package domain.gameObjects.powerUps;

public class HintPower extends PowerUp {

    private int id;

    private int numHint;

    public HintPower(int numHint) {
        id = 3;
        this.numHint = numHint;
    }



    public int getNumHint() {
        return numHint;
    }

    public void setNumHint(int numHint) {
        this.numHint = numHint;
    }


    @Override
    public int getID() {
        return this.id;
    }

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

    @Override
    public int getNum() {
            return this.numHint;
    }
}
