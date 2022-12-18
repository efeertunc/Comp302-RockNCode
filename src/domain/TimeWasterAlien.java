package domain;

import HelperComponents.Position;

import java.awt.image.BufferedImage;
import java.io.ObjectStreamClass;
import java.util.ArrayList;
import java.util.Random;

public class TimeWasterAlien extends Alien{

    final int cooldown = 5;
    public double counter;
    public boolean ready;
    public TimeWasterAlien(int x, int y, BufferedImage image) {
        this.position = new Position();
        this.position.setPos(x,y);
        this.image = image;
    }



    public void ChangeKeyPos(Building building)
    {
        if (building.keyPos == null)
        {
            return;
        }
        Obstacle obstacle = (Obstacle) building.getMap_obj()[building.keyPos.getY()][building.keyPos.getX()];
        int keyID = obstacle.key.getID();
        obstacle.deleteKey();
        building.setKey();
    }


    @Override
    public void Update(Building building, double intervalTime) {
        if (ready)
        {
            ChangeKeyPos(building);
            ready = false;
            counter= (double)cooldown;
            System.out.println("KEY CHANGED");
        }
        counter -= intervalTime/1000000000;
        if (counter <= 0)
        {
            ready=true;
        }
    }
}
