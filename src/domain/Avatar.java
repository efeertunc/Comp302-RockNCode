package domain;

import HelperComponents.Position;
import main.EscapeFromKoc;
import objects.ObjectTile;
import panels.BuildPanel;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class Avatar extends DynamicTile {
    int life;
    int time;
    double currentTime;
    Bag bag;

    public Avatar(int life, int time, int x, int y, int image)
    {
        this.life = life;
        this.time = time;
        currentTime = (double) time;
        this.position = new Position();
        position.setPos(x,y);
        this.image = image;
    }

    public Position getPosition()
    {
        return position;
    }

    public void move(int x, int y, Building building)
    {
        if (x>=0 && x<17 && y>=0 && y<12) //tile exists
        {
            if (building.getMap_obj()[y][x] instanceof EmptyTile) //tile empty
            {
                int oldX = getPosition().getX();
                int oldY = getPosition().getY();
                building.getMap_obj()[oldY][oldX] = new EmptyTile(oldX, oldY, EscapeFromKoc.getInstance().tm.objects[4].image);
                building.getMap_obj()[y][x] = this;
                getPosition().setPos(x,y);
            }
        }
    }

    public boolean searchKey(int x, int y, Building building)
    {
        int xDiff = Math.abs(position.getX() - x);
        int yDiff = Math.abs(position.getY() - y);
        if (xDiff <= 1 && yDiff <= 1) //reachable
        {
            if (building.checkObstacle(x,y) != null)//it's obstacle
            {
                if (building.checkObstacle(x,y).key != null) //hasKey
                {
                    System.out.println("KEY HAS BEEN FOUND");
                    this.image = EscapeFromKoc.getInstance().tm.getObjects()[6].image;
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
