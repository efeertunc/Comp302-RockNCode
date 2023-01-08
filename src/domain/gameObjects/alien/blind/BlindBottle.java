package domain.gameObjects.alien.blind;

public class BlindBottle implements  BlindBehavior{

    BlindAlien alien;

    public BlindBottle(BlindAlien alien){
        this.alien = alien;
    }
    @Override
    public void action(double interval) {

    }
}
