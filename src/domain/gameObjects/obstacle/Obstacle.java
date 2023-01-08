package domain.gameObjects.obstacle;

import domain.gameObjects.ObjectTile;
import helperComponents.Position;
import main.EscapeFromKoc;

public class Obstacle extends ObjectTile {

    private Key key;
    private int type;
    private int savedImage;

    public Obstacle(int type, int x, int y, int image)
    {
        this.type = type;
        setPosition(new Position());
        getPosition().setPos(x,y);
        setImage(image);
    }
    public Key getKey() {
        return key;
    }
    public int getSavedImage() {
        return savedImage;
    }
    public int getType() {
        return type;
    }

    public void generateKey(int id)
    {
        this.key = new Key(id);
        savedImage = getImage();
        setImage(8);
    }
    public void deleteKey()
    {
        this.key = null;
        setImage(savedImage);
        savedImage = -1;
    }
}
