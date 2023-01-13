package domain.gameObjects.alien.blind;

import domain.building.Building;
import domain.building.BuildingTracker;
import domain.gameObjects.EmptyTile;
import domain.gameObjects.ObjectTile;
import domain.gameObjects.avatar.Avatar;
import domain.gameObjects.obstacle.Obstacle;
import factory.PanelType;
import factory.ViewType;
import main.EscapeFromKoc;
import panels.RunPanel;

import java.util.ArrayList;
import java.util.Random;

public class BlindNormal extends BaseBlindBehavior implements BlindBehavior{
    ArrayList<ObjectTile> adjObjects;
    Random rand;

    private int cooldown = 1;
    private boolean ready = false;
    private double counter = cooldown;


    public BlindNormal(BlindAlien alien){
        super(alien);
        alien.setImage(10);
        adjObjects = new ArrayList<ObjectTile>();
        rand = new Random();
    }
    @Override
    public void action(double interval) {
        actionFunction(interval);
    }


    public void coroutine(double intervalTime) {
        if (ready)
        {
            alienMove();
            ready = false;
            counter= (double)cooldown;
        }
        counter -= intervalTime/1000000000;
        if (counter <= 0)
        {
            ready=true;
        }
    }

    public void alienMove()
    {
        Building building = BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex());
        //top
        adjObjects.clear();
        int x = getAlien().getPosition().getX();
        int y = getAlien().getPosition().getY() + 1;
        if (x>=0 && x<17 && y>=0 && y<12) //tile exists
        {
            if (building.getMap_obj()[y][x] instanceof EmptyTile) //tile empty
            {
                adjObjects.add(building.getMap_obj()[y][x]);
            }
        }
        //bot
        x = getAlien().getPosition().getX();
        y = getAlien().getPosition().getY() - 1;
        if (x>=0 && x<17 && y>=0 && y<12) //tile exists
        {
            if (building.getMap_obj()[y][x] instanceof EmptyTile) //tile empty
            {
                adjObjects.add(building.getMap_obj()[y][x]);
            }
        }
        //right
        x = getAlien().getPosition().getX()+1;
        y = getAlien().getPosition().getY();
        if (x>=0 && x<17 && y>=0 && y<12) //tile exists
        {
            if (building.getMap_obj()[y][x] instanceof EmptyTile) //tile empty
            {
                adjObjects.add(building.getMap_obj()[y][x]);
            }
        }
        //left
        x = getAlien().getPosition().getX() -1;
        y = getAlien().getPosition().getY();
        if (x>=0 && x<17 && y>=0 && y<12) //tile exists
        {
            if (building.getMap_obj()[y][x] instanceof EmptyTile) //tile empty
            {
                adjObjects.add(building.getMap_obj()[y][x]);
            }
        }
        if (adjObjects.size() == 0)
        {
            return;
        }
        int index = rand.nextInt(adjObjects.size());
        ObjectTile obj= adjObjects.get(index);
        move(obj.getPosition().getX(),obj.getPosition().getY(),building);
    }

    public void move(int x, int y, Building building)
    {
        if (x>=0 && x<17 && y>=0 && y<12) //tile exists
        {
            if (building.getMap_obj()[y][x] instanceof EmptyTile) //tile empty
            {
                int oldX = getAlien().getPosition().getX();
                int oldY = getAlien().getPosition().getY();
                building.getMap_obj()[oldY][oldX] = new EmptyTile(oldX, oldY, 4);
                building.getMap_obj()[y][x] = getAlien();
                getAlien().getPosition().setPos(x,y);
            }
        }
    }

    @Override
    public void trueStateAction(double interval) {
        getAlien().setBehavior(new BlindAttack(getAlien()));
    }

    @Override
    public void falseStateAction(double interval) {
        coroutine(interval);
    }
}
