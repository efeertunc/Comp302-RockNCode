package domain;

public class TimeWasteHard implements TimeWasteBehavior{
    TimeWasterAlien alien;
    int cooldown = 3;
    boolean ready = false;
    double counter = cooldown;

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
