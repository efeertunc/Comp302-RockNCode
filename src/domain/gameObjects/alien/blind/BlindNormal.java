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

public class BlindNormal implements BlindBehavior{

    BlindAlien alien;
    ArrayList<ObjectTile> adjObjects;
    Random rand;

    private int cooldown = 1;
    private boolean ready = false;
    private double counter = cooldown;


    public BlindNormal(BlindAlien alien){
        this.alien = alien;
        alien.setImage(10);
        adjObjects = new ArrayList<ObjectTile>();
        rand = new Random();
    }
    @Override
    public void action(double interval) {
        Avatar avatar = ((RunPanel)EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Run)).getRunController().getAvatar();
        int xDif = Math.abs(avatar.getPosition().getX() - alien.getPosition().getX());
        int yDif =Math.abs(avatar.getPosition().getY() - alien.getPosition().getY());
        double distance = Math.sqrt((double)(Math.pow(xDif,2) + Math.pow(yDif,2)));
        if (distance<= Math.sqrt(2))
        {
            alien.setBehavior(new BlindAttack(alien));
        }
        else
        {
            coroutine(interval);
        }
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
        int x = alien.getPosition().getX();
        int y = alien.getPosition().getY() + 1;
        if (x>=0 && x<17 && y>=0 && y<12) //tile exists
        {
            if (building.getMap_obj()[y][x] instanceof EmptyTile) //tile empty
            {
                adjObjects.add(building.getMap_obj()[y][x]);
            }
        }
        //bot
        x = alien.getPosition().getX();
        y = alien.getPosition().getY() - 1;
        if (x>=0 && x<17 && y>=0 && y<12) //tile exists
        {
            if (building.getMap_obj()[y][x] instanceof EmptyTile) //tile empty
            {
                adjObjects.add(building.getMap_obj()[y][x]);
            }
        }
        //right
        x = alien.getPosition().getX()+1;
        y = alien.getPosition().getY();
        if (x>=0 && x<17 && y>=0 && y<12) //tile exists
        {
            if (building.getMap_obj()[y][x] instanceof EmptyTile) //tile empty
            {
                adjObjects.add(building.getMap_obj()[y][x]);
            }
        }
        //left
        x = alien.getPosition().getX() -1;
        y = alien.getPosition().getY();
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
                int oldX = alien.getPosition().getX();
                int oldY = alien.getPosition().getY();
                building.getMap_obj()[oldY][oldX] = new EmptyTile(oldX, oldY, EscapeFromKoc.getInstance().tm.objects[4].getImage());
                building.getMap_obj()[y][x] = alien;
                alien.getPosition().setPos(x,y);
            }
        }
    }
}
