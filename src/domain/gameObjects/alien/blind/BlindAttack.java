package domain.gameObjects.alien.blind;

import domain.SoundManager;
import domain.gameObjects.ObjectTile;
import domain.gameObjects.alien.AlienType;
import domain.gameObjects.avatar.Avatar;
import factory.PanelType;
import factory.ViewType;
import main.EscapeFromKoc;
import panels.RunPanel;

import java.util.ArrayList;
import java.util.Random;

public class BlindAttack implements BlindBehavior{

    BlindAlien alien;
    private int cooldown = 4;
    private boolean ready = false;
    private double counter = cooldown;
    private double range = Math.sqrt(2);

    public BlindAttack(BlindAlien alien){
        this.alien = alien;
        alien.setImage(3);
    }
    @Override
    public void action(double interval) {
        Avatar avatar = ((RunPanel)EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Run)).getRunController().getAvatar();
        int xDif = Math.abs(avatar.getPosition().getX() - alien.getPosition().getX());
        int yDif =Math.abs(avatar.getPosition().getY() - alien.getPosition().getY());
        double distance = Math.sqrt((double)(Math.pow(xDif,2) + Math.pow(yDif,2)));
        if (distance>range)
        {
            alien.setBehavior(new BlindNormal(alien));
        }
        else
        {
            coroutine(interval);
        }
    }


    public void coroutine(double intervalTime) {
        if (ready)
        {
            alienDamage();
            ready = false;
            counter= (double)cooldown;
        }
        counter -= intervalTime/1000000000;
        if (counter <= 0)
        {
            ready=true;
        }
    }
    public void alienDamage()
    {
        Avatar avatar = ((RunPanel)EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Run)).getRunController().getAvatar();
        avatar.getVestState().takeDamage(AlienType.BLIND, 2);
        System.out.println("Damage given by blind!");
    }
}
