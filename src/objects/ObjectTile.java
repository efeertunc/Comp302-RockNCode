package objects;

import HelperComponents.Position;

import java.awt.image.BufferedImage;

public class ObjectTile {
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
