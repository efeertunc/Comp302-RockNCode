package domain;

public class AlienGenerator {

    int cooldown = 10;
    boolean ready = false;
    double counter = cooldown;
    public void generateAlien(double intervalTime) {
        if (ready)
        {
            BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).generateAlien();
            ready = false;
            counter= (double)cooldown;
            System.out.println("ALIEN GENERATED");
        }
        counter -= intervalTime/1000000000;
        if (counter <= 0)
        {
            ready=true;
        }
    }
}
