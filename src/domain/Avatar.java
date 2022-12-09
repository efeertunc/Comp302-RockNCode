package domain;

import HelperComponents.Position;

public class Avatar {
    int life;
    int time;
    Position position;
    Bag bag;

    public Avatar(int life, int time, int x, int y)
    {
        this.life = life;
        this.time = time;
        this.position = new Position();
        position.setPos(x,y);
    }

    public Position getPosition()
    {
        return position;
    }

    public void move(int x, int y, Building building)
    {
        if (x>=0 && x<17 && y>=0 && y<12) //tile exists
        {
            if (building.getMap()[y][x] == 0) //tile empty
            {
                int oldX = getPosition().getX();
                int oldY = getPosition().getY();
                building.getMap()[oldY][oldX] = 0;
                getPosition().setPos(x,y);
                building.getMap()[y][x] = 6;
            }
        }
    }
}
