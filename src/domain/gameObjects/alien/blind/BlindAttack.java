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

public class BlindAttack extends BaseBlindBehavior implements BlindBehavior{

    private int cooldown = 2;
    private boolean ready = false;
    private double counter = cooldown;
    private double range = Math.sqrt(2);

    public BlindAttack(BlindAlien alien){
        super(alien);
        alien.setImage(14);
    }
    @Override
    public void action(double interval) {
       actionFunction(interval);
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
        getAlien().getSound().playSoundEffect(10);
        avatar.getVestState().takeDamage(AlienType.BLIND, 2);
        System.out.println("Damage given by blind!");
    }

    @Override
    public void trueStateAction(double interval) {
        coroutine(interval);
    }

    @Override
    public void falseStateAction(double interval) {
        getAlien().setBehavior(new BlindNormal(getAlien()));
    }
}
