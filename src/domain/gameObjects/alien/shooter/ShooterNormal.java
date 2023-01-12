package domain.gameObjects.alien.shooter;

public class ShooterNormal extends BaseShooterBehavior implements  ShooterBehavior{
    public ShooterNormal(ShooterAlien alien) {
        super(alien);
        alien.setImage(9);
    }

    @Override
    public void action(double interval) {
        actionFunction(interval);
    }

    @Override
    public void trueStateAction(double interval) {
        getAlien().setBehavior(new ShooterAttack(getAlien()));
    }

    @Override
    public void falseStateAction(double interval) {
        //nothing
    }
}
