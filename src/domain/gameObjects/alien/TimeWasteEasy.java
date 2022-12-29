package domain.gameObjects.alien;

import domain.building.BuildingTracker;

public class TimeWasteEasy implements TimeWasteBehavior{

    TimeWasterAlien alien;
    double countDown = 3;


    public TimeWasteEasy(TimeWasterAlien alien)
    {
        this.alien = alien;
    }
    @Override
    public void timeWaste(double intervalTime) {
        countDown -=  intervalTime/1000000000;
        if (countDown <= 0)
        {
            ChangeKeyPos(BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()));
            alien.vanish();
        }
    }
}
