package domain;

public interface TimeWasteBehavior {

    public void timeWaste(double intervalTime);


    public default void ChangeKeyPos(Building building)
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
}
