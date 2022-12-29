package domain.gameObjects;

import helperComponents.Position;
import java.io.Serializable;

public class ObjectTile implements Serializable {
    public int image;
    public Position position;
    public int ID;

    public int getImage() {
        return image;
    }


    public ObjectTile setImage(int imageId) {
        this.image = imageId;
        return this;
    }
}