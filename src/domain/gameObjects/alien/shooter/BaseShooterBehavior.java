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
                y1 = avatar.getPosition().getY();
                x1 = avatar.getPosition().getX();
                if (Math.abs(xDif)>= Math.abs(yDif)) //xDif selected
                {
                    y1 = y1 + lineCast*0.5;
                    m = createLineM(x1,y1,x2,y2);
                    b = createLineB(x1,y1,m);
                    sign = xDif/Math.abs(xDif);
                    for (int i = 1; i<Math.abs(xDif);i++)
                    {
                        double x3 = x2+i*sign;
                        double y3 = x3*m+b;
                        int indexX = (int)x3;
                        int indexY;
                        if (Math.abs((Math.ceil(y3)-y3)-0.5)<=0.01) //middle point
                        {
                            if (yDif>0)//alien bottom to the line
                            {
                                //pick top one
                                indexY = (int)Math.ceil(y3);
                            }
                            else //alien top to the line
                            {
                                //pick bottom one
                                indexY= (int)Math.floor(y3);
                            }
                        }
                        else
                        {
                            indexY = (int)Math.round(y3);
                        }
                        //System.out.println("xDif: "+xDif+" yDif: "+yDif+" m,b: "+m + " "+ b + " x1,y1: "+x1+" "+y1 + " x2,y2: "+x2+" "+y2+" x3,y3: "+x3+" "+y3+ " indexX, indexY: "+indexX +" "+indexY );
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
                    for (int i = 1; i<Math.abs(yDif);i++)
                    {
                        double y3 = y2+i*sign;
                        double x3 = (y3-b)/m;
                        int indexY = (int)y3;
                        int indexX;
                        if (Math.abs((Math.ceil(x3)-x3)-0.5)<=0.01) //middle point
                        {
                            if (xDif>0)//alien left to the line
                            {
                                //pick right one
                                indexX = (int)Math.ceil(x3);
                            }
                            else //alien right to the line
                            {
                                //pick left one
                                indexX = (int)Math.floor(x3);
                            }
                        }
                        else
                        {
                            indexX = (int)Math.round(x3);
                        }
                        //System.out.println("xDif: "+xDif+" yDif: "+yDif+" m,b: "+m + " "+ b + " x1,y1: "+x1+" "+y1 + " x2,y2: "+x2+" "+y2+" x3,y3: "+x3+" "+y3+ " indexX, indexY: "+indexX +" "+indexY );

                        if (map_obj[indexY][indexX] instanceof Obstacle)
                        {
                            failCount +=1;
                            break;
                        }
                    }
                }
            }
            if (failCount >2)
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
        return (y2-y1)/(x2-x1);
    }

    public double createLineB(double x1, double y1, double m)
    {
        return y1 - (m*x1);
    }
}
