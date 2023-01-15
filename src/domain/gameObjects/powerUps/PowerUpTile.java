package domain.gameObjects.powerUps;

import domain.building.Building;
import domain.building.BuildingTracker;
import domain.gameObjects.DynamicTile;
import domain.gameObjects.EmptyTile;
import domain.gameObjects.ObjectTile;
import factory.PanelType;
import factory.PowerUpFactory;
import factory.ViewType;
import helperComponents.Position;
import main.EscapeFromKoc;
import panels.RunPanel;

public class PowerUpTile extends DynamicTile {

    private PowerUpTypes type;
    private double time;

    public void setType(PowerUpTypes type) {
        this.type = type;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }


    public PowerUpTile(PowerUpTypes type, int x, int y, int image) {
        setPosition(new Position(x,y));
        setImage(image);
        this.type = type;
        time = 6;
    }

    public PowerUpTile(PowerUpTypes type, int x, int y, int image, double time) {
        setPosition(new Position(x,y));
        setImage(image);
        this.type = type;
        this.time = time;
    }

    public PowerUpTypes getType() {
        return type;
    }


    public void clicked() {
        if (type == PowerUpTypes.HINT){
            BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar().getBag().addPowerUp(type);
        }
        else if (type == PowerUpTypes.BOTTLE){
            BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar().getBag().addPowerUp(type);
        }
        else if (type == PowerUpTypes.VEST){
            BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar().getBag().addPowerUp(type);
        }
        else {
            PowerUpFactory.getInstance().createPowerUp(type).use();
        }
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
