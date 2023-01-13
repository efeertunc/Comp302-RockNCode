package domain.gameObjects.alien.shooter;

import domain.gameObjects.alien.Alien;
import domain.gameObjects.alien.AlienType;
import domain.gameObjects.avatar.Avatar;
import factory.PanelType;
import factory.ViewType;
import main.EscapeFromKoc;
import panels.RunPanel;

public class ShooterAlien extends Alien {
    private int range = 4;
    ShooterBehavior behavior;


    public ShooterAlien(int x, int y, int image)
    {
        super(x,y,image);
        setBehavior(new ShooterAttack(this));
    }

    @Override
    public void update(double interval) {
        behavior.action(interval);
    }




    public void setBehavior(ShooterBehavior behavior)
    {
        this.behavior = behavior;
    }

    public int getRange()
    {
        return range;
    }
}
