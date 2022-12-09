package domain;

import HelperComponents.Position;

public class Obstacle {

    public Position position;
    public Key key;

    public int type;

    public Obstacle(int type, int x, int y)
    {
        this.type = type;
        this.position = new Position();
        position.setPos(x,y);
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
