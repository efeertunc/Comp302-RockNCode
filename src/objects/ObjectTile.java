package objects;

import java.awt.image.BufferedImage;

public class ObjectTile {
    public BufferedImage image;

    public BufferedImage getImage() {
        return image;
    }

    public ObjectTile setImage(BufferedImage image) {
        this.image = image;
        return this;
    }
}
