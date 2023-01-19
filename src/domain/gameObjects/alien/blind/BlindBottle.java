package domain.gameObjects.alien.blind;

import domain.building.Building;
import domain.building.BuildingTracker;
import domain.gameObjects.EmptyTile;
import domain.gameObjects.ObjectTile;
import helperComponents.Position;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class BlindBottle extends BaseBlindBehavior implements  BlindBehavior{


    private ArrayList<ObjectTile> visitedList;
    private ArrayList<ObjectTile> manhattenList;
    ArrayList<ObjectTile> adjObjects;
    Random rand;

    private int cooldown = 1;
    private boolean ready = false;
    private double counter = cooldown;

    private int cooldown2 = 20;
    private boolean ready2 = false;
    private double counter2 = cooldown2;

    private Position destination;

    public BlindBottle(BlindAlien alien, Position pos){
        super(alien);
        alien.setImage(13);
        visitedList = new ArrayList<ObjectTile>();
        adjObjects = new ArrayList<ObjectTile>();
        manhattenList = new ArrayList<ObjectTile>();
        destination = pos;
        rand = new Random();
    }
    @Override
    public void action(double interval) {
        actionFunction(interval);
    }

    public void coroutine(double intervalTime) {
        if (ready2)
        {
            getAlien().setBehavior(new BlindNormal(getAlien()));
            return;
        }
        if (ready)
        {
            alienMove();
            ready = false;
            counter= (double)cooldown;
        }
        counter -= intervalTime/1000000000;
        counter2 -= intervalTime/1000000000;
        if (counter <= 0)
        {
            ready=true;
        }
        if (counter2 <= 0)
        {
            ready2 = true;
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

        manhattanCalculation(building);
    }

    private void manhattanCalculation(Building building)
    {
        if (adjObjects.size() ==0)
        {
            System.out.println("ERROR IN MANHATTEN MOVEMENT");
            return;
        }
        manhattenList.clear();
        for (int i = 0; i<adjObjects.size();i++)
        {
            if (distance(getAlien().getPosition(),destination) > distance(adjObjects.get(i).getPosition(),destination))
            {
                manhattenList.add(adjObjects.get(i));
            }
        }
        midControl(building);
    }



    private void midControl(Building building)
    {
        ArrayList<ObjectTile> l1 = new ArrayList<ObjectTile>();
        ArrayList<ObjectTile> l2 = new ArrayList<ObjectTile>();
        ArrayList<ObjectTile> l3 = new ArrayList<ObjectTile>();
        ArrayList<ObjectTile> l4 = new ArrayList<ObjectTile>();

        for (int i =0 ; i <adjObjects.size();i++)
        {
            boolean inVisited = visitedList.contains(adjObjects.get(i));
            boolean inManhatten = manhattenList.contains(adjObjects.get(i));
            if (!inVisited && inManhatten)
            {
                l1.add(adjObjects.get(i));
            }
            else if (!inVisited && !inManhatten)
            {
                l2.add(adjObjects.get(i));
            }
            else if (inVisited && inManhatten)
            {
                l3.add(adjObjects.get(i));
            }
            else
            {
                l4.add(adjObjects.get(i));
            }
        }
        if (l1.size()!=0)
        {
            int index = rand.nextInt(l1.size());
            ObjectTile selected = l1.get(index);
            move(selected.getPosition().getX(),selected.getPosition().getY(),building);
            visitedList.add(selected);
        }
        else if (l2.size()!=0)
        {
            int index = rand.nextInt(l2.size());
            ObjectTile selected = l2.get(index);
            move(selected.getPosition().getX(),selected.getPosition().getY(),building);
            visitedList.add(selected);
        }
        else if (l3.size()!=0)
        {
            int index = rand.nextInt(l3.size());
            ObjectTile selected = l3.get(index);
            move(selected.getPosition().getX(),selected.getPosition().getY(),building);
        }
        else if (l4.size()!=0)
        {
            int index = rand.nextInt(l4.size());
            ObjectTile selected = l4.get(index);
            move(selected.getPosition().getX(),selected.getPosition().getY(),building);
        }
        else
        {
            System.out.println("ERROR IN MANHATTEN MOVEMENT IN BLIND ALIEN");
        }
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

    private double distance(Position p1, Position p2)
    {
        double x1 = p1.getX();
        double y1 = p1.getY();
        double x2 = p2.getX();
        double y2 = p2.getY();

        double distance = Math.sqrt(Math.pow((x1-x2),2) + Math.pow((y1-y2),2));
        return distance;
    }
}
