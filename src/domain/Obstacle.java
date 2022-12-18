package domain;

import HelperComponents.Position;
import main.EscapeFromKoc;
import objects.ObjectTile;

import java.awt.image.BufferedImage;

public class Obstacle extends ObjectTile {
    public Key key;

    public int type;
    BufferedImage savedImage;

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
        savedImage = this.image;
        this.image = EscapeFromKoc.getInstance().tm.objects[8].image;
    }
    public void deleteKey()
    {
        this.key = null;
        this.image = savedImage;
        savedImage = null;
    }
}
