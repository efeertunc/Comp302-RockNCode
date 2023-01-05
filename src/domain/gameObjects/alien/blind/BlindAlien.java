package domain.gameObjects.alien.blind;

import domain.building.Building;
import domain.building.BuildingTracker;
import domain.gameObjects.EmptyTile;
import domain.gameObjects.ObjectTile;
import domain.gameObjects.alien.Alien;
import domain.gameObjects.avatar.Avatar;
import factory.PanelType;
import factory.ViewType;
import main.EscapeFromKoc;
import panels.RunPanel;

import java.util.ArrayList;
import java.util.Random;

public class BlindAlien extends Alien {

    BlindBehavior blindBehavior;

    public BlindAlien(int x, int y, int image) {
        super(x,y,image);
        setBehavior(new BlindNormal(this));
    }
    @Override
    public void update(double interval) {
        blindBehavior.action(interval);
    }
    public void setBehavior(BlindBehavior behavior)
    {
        this.blindBehavior = behavior;
    }

}
