package domain.gameObjects.avatar;

import domain.gameObjects.DynamicTile;
import domain.gameObjects.EmptyTile;
import helperComponents.Position;
import domain.building.Building;
import main.EscapeFromKoc;

public class Avatar extends DynamicTile {
    private int life;
    private int time;
    private double currentTime;
    private Bag bag;

    public Avatar(int life, int time, int x, int y, int image)
    {
        this.life = life;
        this.time = time;
        currentTime = (double) time;
        setPosition(new Position(x,y));
        setImage(image);
    }
    public int getTime() {
        return time;
    }

    public double getCurrentTime() {
        return currentTime;
    }

    public boolean move(int x, int y, Building building) {
        if (x >= 0 && x < 17 && y >= 0 && y < 12) //tile exists
        {
            if (building.getMap_obj()[y][x] instanceof EmptyTile) //tile empty
            {
                System.out.println("moved");
                int oldX = getPosition().getX();
                int oldY = getPosition().getY();
                building.getMap_obj()[oldY][oldX] = new EmptyTile(oldX, oldY, EscapeFromKoc.getInstance().tm.objects[4].getImage());
                building.getMap_obj()[y][x] = this;
                getPosition().setPos(x, y);
                if (building.getMap_obj()[y][x] instanceof Avatar) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        else{
            throw new IllegalArgumentException("Map_obj out of bounds");
        }
        return false;
    }


    public boolean searchKey(int x, int y, Building building)
    {
        int xDiff = Math.abs(getPosition().getX() - x);
        int yDiff = Math.abs(getPosition().getY() - y);
        if (xDiff <= 1 && yDiff <= 1) //reachable
        {
            if (building.checkObstacle(x,y) != null)//it's obstacle
            {
                if (building.checkObstacle(x,y).getKey() != null) //hasKey
                {
                    System.out.println("KEY HAS BEEN FOUND");
                    setImage(EscapeFromKoc.getInstance().tm.getObjects()[6].getImage());
                    building.deleteKey();
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void update(double intervalTime) {
        currentTime -=  intervalTime/1000000000;
        if (currentTime < 0)
        {
            currentTime = 0;
        }
    }
}
