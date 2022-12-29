package domain.gameObjects.alien;

import domain.building.BuildingTracker;

public class TimeWasteHard implements TimeWasteBehavior{
    private TimeWasterAlien alien;
    private int cooldown = 3;
    private boolean ready = false;
    private double counter = cooldown;

    public TimeWasteHard(TimeWasterAlien alien)
    {
        this.alien = alien;
    }
    @Override
    public void timeWaste(double intervalTime) {
        if (ready)
        {
            ChangeKeyPos(BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()));
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
