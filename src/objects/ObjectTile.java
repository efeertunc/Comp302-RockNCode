package objects;

import HelperComponents.Position;

import java.awt.image.BufferedImage;

public class ObjectTile {
    public BufferedImage image;
    public Position position;
    public int ID;

    public BufferedImage getImage() {
        return image;
    }


    public ObjectTile setImage(BufferedImage image) {
        this.image = image;
        return this;
    }
}
