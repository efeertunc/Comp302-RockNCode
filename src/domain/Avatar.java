package domain;

import HelperComponents.Position;

public class Avatar {
    int life;
    int time;
    Position position;
    Bag bag;
    int matrixCode = 6;

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
                building.getMap()[y][x] = getMatrixCode();
            }
        }
    }

    public void searchKey(int x, int y, Building building)
    {
        int xDiff = Math.abs(position.getX() - x);
        int yDiff = Math.abs(position.getY() - y);
        if (xDiff <= 1 && yDiff <= 1) //reachable
        {
            if (building.checkObstacle(x,y).key != null)
            {
                System.out.println("KEY HAS BEEN FOUND");
                setMatrixCode(7);
                building.getMap()[position.getY()][position.getX()] = 7;
            }
        }
    }
    public int getMatrixCode()
    {
        return this.matrixCode;
    }
    public void setMatrixCode(int code)
    {
        this.matrixCode = code;
    }
}
