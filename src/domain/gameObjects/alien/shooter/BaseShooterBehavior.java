package domain.gameObjects.alien.shooter;

import domain.building.BuildingTracker;
import domain.gameObjects.ObjectTile;
import domain.gameObjects.avatar.Avatar;
import domain.gameObjects.obstacle.Obstacle;
import factory.PanelType;
import factory.ViewType;
import main.EscapeFromKoc;
import panels.RunPanel;

public abstract class BaseShooterBehavior {
    private ShooterAlien alien;
    public BaseShooterBehavior(ShooterAlien alien)
    {
        this.alien = alien;
    }


    public ShooterAlien getAlien()
    {
        return alien;
    }


    public void actionFunction(double interval)
    {
        Avatar avatar = ((RunPanel) EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Run)).getRunController().getAvatar();
        ObjectTile[][] map_obj = BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getMap_obj();
        int xDif = avatar.getPosition().getX() - getAlien().getPosition().getX();
        int yDif = avatar.getPosition().getY() - getAlien().getPosition().getY();
        double distance = Math.sqrt(( Math.pow(xDif,2) + Math.pow(yDif,2)));
        if (distance >= getAlien().getRange())
        {
            falseStateAction(interval);
            return;
        }
        else if (xDif == 0) //x constant
        {
            int sign = yDif/Math.abs(yDif);
            for (int i = 1; i <Math.abs(yDif);i++)
            {
                int xIndex = getAlien().getPosition().getX();
                int yIndex = getAlien().getPosition().getY() + i*sign;
                if (map_obj[yIndex][xIndex] instanceof Obstacle)
                {
                    falseStateAction(interval);
                    return;
                }
            }
        }
        else if (yDif == 0)//x constant
        {
            int sign = xDif/Math.abs(xDif);
            for (int i = 1; i <Math.abs(xDif);i++)
            {
                int yIndex = getAlien().getPosition().getY();
                int xIndex = getAlien().getPosition().getX() + i*sign;
                if (map_obj[yIndex][xIndex] instanceof Obstacle)
                {
                    falseStateAction(interval);
                    return;
                }
            }
        }
        else //construct a line
        {
            double x1 = avatar.getPosition().getX();
            double y1 = avatar.getPosition().getY();
            double x2 = getAlien().getPosition().getX();
            double y2 = getAlien().getPosition().getY();

            double m;
            double b;
            int sign;
            int failCount=0;
            for (int lineCast = -1; lineCast<2;lineCast++)
            {
                if (Math.abs(xDif)>= Math.abs(yDif)) //xDif selected
                {
                    y1 = y1 + lineCast*0.5;
                    m = createLineM(x1,y1,x2,y2);
                    b = createLineB(x1,y1,m);
                    sign = xDif/Math.abs(xDif);
                    for (int i = 0; i<Math.abs(xDif);i++)
                    {
                        double x3 = x2+i*sign;
                        double y3 = x3*m+b;
                        int indexX = (int)x3;
                        int indexY = (int)Math.round(y3);
                        if (map_obj[indexY][indexX] instanceof Obstacle)
                        {
                            failCount +=1;
                            break;
                        }
                    }
                }
                else//yDif selected
                {
                    x1 = x1 + lineCast*0.5;
                    m = createLineM(x1,y1,x2,y2);
                    b = createLineB(x1,y1,m);
                    sign = yDif/Math.abs(yDif);
                    for (int i = 0; i<Math.abs(yDif);i++)
                    {
                        double y3 = y2+i*sign;
                        double x3 = y3*m+b;
                        int indexY = (int)y3;
                        int indexX = (int)Math.round(x3);
                        if (map_obj[indexY][indexX] instanceof Obstacle)
                        {
                            failCount +=1;
                            break;
                        }
                    }
                }
            }
            if (failCount >=2)
            {
                falseStateAction(interval);
                return;
            }
        }
        trueStateAction(interval);
    }


    public abstract void trueStateAction(double interval);
    public abstract void falseStateAction(double interval);

    public double createLineM(double x1, double y1, double x2, double y2)
    {
        return (y1-y2)/(x1-x2);
    }

    public double createLineB(double x1, double y1, double m)
    {
        return y1/(m*x1);
    }
}
