package domain.gameObjects.alien;

import domain.building.Building;
import domain.gameObjects.obstacle.Obstacle;

public interface TimeWasteBehavior {

    public void timeWaste(double intervalTime);


    public default void ChangeKeyPos(Building building)
    {
        if (building.getKeyPos() == null)
        {
            return;
        }
        Obstacle obstacle = (Obstacle) building.getMap_obj()[building.getKeyPos().getY()][building.getKeyPos().getX()];
        int keyID = obstacle.key.getID();
        obstacle.deleteKey();
        building.setKey();
    }
}
