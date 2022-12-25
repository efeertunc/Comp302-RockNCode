package domain;

import HelperComponents.Position;
import main.EscapeFromKoc;
import objects.TileManager;

import java.awt.image.BufferedImage;
import java.io.ObjectStreamClass;
import java.util.ArrayList;
import java.util.Random;

public class TimeWasterAlien extends Alien{


    TimeWasteBehavior behavior;

    public TimeWasterAlien(int x, int y, BufferedImage image) {

        this.position = new Position();
        this.position.setPos(x,y);
        this.image = image;
        updateBehavior();
    }
    @Override
    public void update(double intervalTime) {
        updateBehavior();
        behavior.timeWaste(intervalTime);
    }

    public void setBehavior(TimeWasteBehavior newBehavior)
    {
        if (behavior == null)
        {
            behavior = newBehavior;
            System.out.println("Assigned behavior: "+ behavior.getClass().getSimpleName());
        }
        else
        {
            if (!behavior.getClass().isAssignableFrom(newBehavior.getClass()))
            {
                behavior = newBehavior;
                System.out.println("Updated behavior: "+ behavior.getClass().getSimpleName());
            }
        }
    }

    public void updateBehavior()
    {
        Avatar avatar= BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).avatar;
        double percentage = avatar.currentTime/avatar.time * 100;
        if (percentage >=70)
        {
            setBehavior(new TimeWasteHard());
        }
        else if (percentage >= 30)
        {
            setBehavior(new TimeWasteIndecisive(this));
        }
        else if (percentage < 30)
        {
            setBehavior(new TimeWasteEasy(this));
        }
    }


    public void vanish()
    {
        Building b = BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex());
        b.getMap_obj()[position.getY()][position.getX()] = new EmptyTile(position.getX(),position.getY(), EscapeFromKoc.getInstance().tm.objects[4].image);
        System.out.println("Alien Vanished");
    }
}
