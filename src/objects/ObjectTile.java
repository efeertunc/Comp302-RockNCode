package objects;

import HelperComponents.Position;

import java.awt.image.BufferedImage;

public class ObjectTile {
    public BufferedImage image;
    Position position;

    public BufferedImage getImage() {
        return image;
    }


    public ObjectTile setImage(BufferedImage image) {
        this.image = image;
        return this;
    }
}
