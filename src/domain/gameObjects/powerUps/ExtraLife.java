package domain.gameObjects.powerUps;

import domain.building.Building;
import domain.building.BuildingTracker;

public class ExtraLife extends PowerUp {

    private int id;
    private int numExtraLife;

    @Override
    public void use() {
        System.out.println("Life1: "+BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar().getLife());

        if (BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar().getLife() < 5) {
            BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar().addLife(1);
        }
        System.out.println("Life2: "+BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar().getLife());
    }

}
