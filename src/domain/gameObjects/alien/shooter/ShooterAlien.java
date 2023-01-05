package domain.gameObjects.alien.shooter;

import domain.building.BuildingTracker;
import domain.controllers.RunController;
import domain.gameObjects.EmptyTile;
import domain.gameObjects.ObjectTile;
import domain.gameObjects.alien.Alien;
import domain.gameObjects.avatar.Avatar;
import factory.PanelType;
import factory.ViewType;
import main.EscapeFromKoc;
import panels.RunPanel;

public class ShooterAlien extends Alien {

    private int cooldown = 1;
    private boolean ready = false;
    private double counter = cooldown;
    private int range = 4;


    public ShooterAlien(int x, int y, int image)
    {
        super(x,y,image);
    }

    @Override
    public void update(double interval) {
        shooterAttack(interval);
    }

    public void shooterAttack(double intervalTime) {
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
        Avatar avatar = ((RunPanel)EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Run)).getRunController().getAvatar();
        int xDif = Math.abs(avatar.getPosition().getX() - getPosition().getX());
        int yDif = Math.abs(avatar.getPosition().getY() - getPosition().getY());
        double distance = Math.sqrt(((double) Math.pow(xDif,2) + Math.pow(yDif,2)));
        if (distance < range)
        {
            avatar.takeDamage(1);
        }

    }
}
