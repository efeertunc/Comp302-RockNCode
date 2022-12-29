package domain.gameObjects;

import helperComponents.Position;
import java.io.Serializable;

public class ObjectTile implements Serializable {
    private int image;
    private Position position;
    private int ID;

    public int getImage() {
        return image;
    }

    public Position getPosition() {
        return position;
    }

    public int getID() {
        return ID;
    }

    public ObjectTile setImage(int imageId) {
        this.image = imageId;
        return this;
    }
    public void setPosition(Position position) {
        this.position = position;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}