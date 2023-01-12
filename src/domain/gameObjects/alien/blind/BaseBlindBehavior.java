package domain.gameObjects.alien.blind;

import domain.gameObjects.alien.shooter.ShooterAlien;
import domain.gameObjects.avatar.Avatar;
import factory.PanelType;
import factory.ViewType;
import main.EscapeFromKoc;
import panels.RunPanel;

public abstract class BaseBlindBehavior {


    private BlindAlien alien;
    public BaseBlindBehavior(BlindAlien alien)
    {
        this.alien = alien;
    }


    public void actionFunction(double interval)
    {
        Avatar avatar = ((RunPanel) EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Run)).getRunController().getAvatar();
        int xDif = Math.abs(avatar.getPosition().getX() - alien.getPosition().getX());
        int yDif =Math.abs(avatar.getPosition().getY() - alien.getPosition().getY());
        double distance = Math.sqrt((double)(Math.pow(xDif,2) + Math.pow(yDif,2)));
        if (distance>getAlien().getRange())
        {
            falseStateAction(interval);
        }
        else
        {
            trueStateAction(interval);
        }
    }

    public BlindAlien getAlien()
    {
        return alien;
    }

    public abstract void trueStateAction(double interval);
    public abstract void falseStateAction(double interval);
}
