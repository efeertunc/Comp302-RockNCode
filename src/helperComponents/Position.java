package helperComponents;

import java.io.Serializable;

public class Position implements Serializable {

    private int x;
    private int y;
    public Position()
    {
        //?
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void addToPos(Position pos)
    {
        this.x += pos.x;
        this.y += pos.y;
    }

    public void addToPos(int x, int y)
    {
        this.x += x;
        this.y += y;
    }

    public void setPos(Position pos)
    {
        this.x = pos.x;
        this.y = pos.y;
    }
    public void setPos(int x , int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return this.x;
    }
    public int getY()
    {
        return this.y;
    }
    public int getUIX()
    {
        return 42 + this.x*48;
    }
    public int getUIY()
    {
        return 27 + this.y*48;
    }
}