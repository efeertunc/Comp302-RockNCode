package domain.gameObjects.alien;

public class TimeWasteIndecisive implements TimeWasteBehavior{

    private TimeWasterAlien alien;
    private double countDown = 2;

    public TimeWasteIndecisive(TimeWasterAlien alien)
    {
        this.alien = alien;
    }
    @Override
    public void timeWaste(double intervalTime) {
        countDown -=    intervalTime/1000000000;
        if (countDown <=0)
        {
            alien.vanish();
        }
    }


}
