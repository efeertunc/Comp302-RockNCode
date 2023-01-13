package domain.gameObjects.alien.shooter;

import domain.building.BuildingTracker;
import domain.gameObjects.ObjectTile;
import domain.gameObjects.alien.AlienType;
import domain.gameObjects.avatar.Avatar;
import domain.gameObjects.obstacle.Obstacle;
import factory.PanelType;
import factory.ViewType;
import main.EscapeFromKoc;
import panels.RunPanel;

public class ShooterAttack extends BaseShooterBehavior implements ShooterBehavior{
    private int cooldown = 1;
    private boolean ready = false;
    private double counter = cooldown;

    public ShooterAttack(ShooterAlien alien) {
        super(alien);
        alien.setImage(13);
        getAlien().getSound().playSoundEffect(6);
    }

    @Override
    public void action(double interval) {
        actionFunction(interval);
    }

    public void coroutine(double intervalTime) {
        if (ready)
        {
            attack();
            ready = false;
            counter= (double)cooldown;
        }
        counter -= intervalTime/1000000000;
        if (counter <= 0)
        {
            ready=true;
        }
    }

    public void attack()
    {
        Avatar avatar = ((RunPanel) EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Run)).getRunController().getAvatar();
        getAlien().getSound().playSoundEffect(7);
        avatar.getVestState().takeDamage(AlienType.SHOOTER, 1);
    }

    @Override
    public void trueStateAction(double interval) {
        coroutine(interval);
    }

    @Override
    public void falseStateAction(double interval) {
        getAlien().setBehavior(new ShooterNormal(getAlien()));
    }
}
