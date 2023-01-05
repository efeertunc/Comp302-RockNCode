package domain.gameObjects.alien;

import domain.gameObjects.avatar.Avatar;
import domain.gameObjects.EmptyTile;
import helperComponents.Position;
import domain.building.Building;
import domain.building.BuildingTracker;
import main.EscapeFromKoc;

public class TimeWasterAlien extends Alien{

    private TimeWasteBehavior behavior;
    private boolean isBehaviorSet = false;

    public TimeWasterAlien(int x, int y, int image) {
        setPosition(new Position(x,y));
        setImage(image);
    }
    @Override
    public void update(double intervalTime) {
        updateBehavior();
        if (isBehaviorSet){
            behavior.timeWaste(intervalTime);
        }
    }

    public void setBehavior(TimeWasteBehavior newBehavior)
    {
        if (behavior == null)
        {
            behavior = newBehavior;
            isBehaviorSet =true;
            System.out.println("Assigned behavior: "+ behavior.getClass().getSimpleName());
        }
        else
        {
            //System.out.println("Behavior is already assigned");
            if (!behavior.getClass().isAssignableFrom(newBehavior.getClass()))
            {
                behavior = newBehavior;
                isBehaviorSet = true;
                System.out.println("Updated behavior: "+ behavior.getClass().getSimpleName());
            }
        }
    }

    public void updateBehavior() {

        Building building = BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex());
        double percentage = (building.getTime()/(5 * building.getNumofObstacles(building.getMap_obj()))) * 100;

        if (percentage >= 70)
        {
            setBehavior(new TimeWasteHard(this));
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
        b.getMap_obj()[getPosition().getY()][getPosition().getX()] = new EmptyTile(getPosition().getX(),getPosition().getY(), 4);
        System.out.println("Alien Vanished");
    }
}
