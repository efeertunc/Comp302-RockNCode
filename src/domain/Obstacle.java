package domain;

import HelperComponents.Position;
import objects.ObjectTile;

import java.awt.image.BufferedImage;

public class Obstacle extends ObjectTile {
    public Key key;

    public int type;

    public Obstacle(int type, int x, int y, BufferedImage image)
    {
        this.type = type;
        this.position = new Position();
        position.setPos(x,y);
        this.image = image;
    }

    public void generateKey(int id)
    {
        this.key = new Key(id);
    }
    public void deleteKey()
    {
        this.key = null;
    }
}
