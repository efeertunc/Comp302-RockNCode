package domain.gameObjects.powerUps;

import domain.building.Building;
import domain.building.BuildingTracker;
import domain.gameObjects.DynamicTile;
import domain.gameObjects.EmptyTile;
import domain.gameObjects.ObjectTile;
import factory.PowerUpFactory;
import helperComponents.Position;

public class PowerUpTile extends DynamicTile {

    private PowerUpTypes type;
    private double time;
    public PowerUpTile(PowerUpTypes type, int x, int y, int image) {
        setPosition(new Position(x,y));
        setImage(image);
        this.type = type;
        time = 6;
    }

    public PowerUpTypes getType() {
        return type;
    }


    public void clicked() {
        PowerUpFactory.getInstance().createPowerUp(type).use();
    }

    @Override
    public void update(double intervalTime) {
        time -=  intervalTime/1000000000;
        if (time < 0) {
            BuildingTracker.getBuildingList()
                    .get(BuildingTracker.getCurrentIndex())
                        .getMap_obj()[getPosition()
                            .getY()][getPosition().getX()] = new EmptyTile(getPosition().getX(),getPosition().getY(),4);
        }
    }
}
