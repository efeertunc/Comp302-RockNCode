package domain.gameObjects.powerUps;

import domain.building.Building;
import domain.building.BuildingTracker;

public class ExtraTime extends PowerUp {

    private int id;
    private int numExtraTime;

    @Override
    public void use() {
        numExtraTime = 5;
        System.out.println("Extra Time use Function");
        Building building = BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex());
        building.setTime(numExtraTime);
    }

}
