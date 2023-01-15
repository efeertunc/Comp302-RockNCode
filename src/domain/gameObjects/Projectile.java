package domain.gameObjects;

import com.google.api.Endpoint;
import com.google.cloud.storage.Acl;
import helperComponents.Position;

import javax.xml.stream.events.EndDocument;
import java.sql.SQLOutput;

public class Projectile {
    private int image;
    private double speed;
    private Position dest;
    private int xPosStart;
    private int yPosStart;
    private int xPosEnd;
    private int yPosEnd;
    private int xVector;
    private int yVector;
    private int totalFrame;
    private int counter;
    int xSpeed;
    int ySpeed;
    boolean isActive;
    public Projectile(Position startPoint, Position endPoint, float speed, int image)
    {
        dest = endPoint;
        counter = 0;
        this.image= image;
        this.speed = speed;
        xPosStart = parseX(startPoint.getX());
        yPosStart = parseY(startPoint.getY());
        xPosEnd = parseX(endPoint.getX());
        yPosEnd = parseY(endPoint.getY());
        xVector = xPosEnd - xPosStart;
        yVector = yPosEnd - yPosStart;
        double magnitude = Math.sqrt(Math.pow(xVector, 2) + Math.pow(yVector, 2));
        totalFrame = (int) (magnitude / speed);
        System.out.println("total frame: "+totalFrame);
        if (xVector == 0)
        {
            xSpeed = 0;
        }
        else
        {
            xSpeed = xVector/totalFrame;
        }
        if (yVector ==0)
        {
            ySpeed = 0;
        }
        else
        {
            ySpeed = yVector/totalFrame;
        }
        isActive=true;
    }

    public int getImage()
    {
        return image;
    }


    public boolean move() {
        if (isActive)
        {
            System.out.println("move call bottle");
            System.out.println(totalFrame);
            System.out.println(counter);
            if (counter < totalFrame)
            {
                xPosStart = xPosStart + xSpeed;
                yPosStart = yPosStart + ySpeed;
                counter +=1;
                System.out.println("one");
                return true;
            }
            else
            {
                System.out.println("two");
                isActive=false;
                return false;
            }
        }
        return true;
    }
    private int parseX(int x)
    {
        return (int)(x*50);
    }
    private int parseY(int y) {
        return (int)(y*50);
    }
    public int getXPos()
    {
        return xPosStart;
    }
    public int getYPos()
    {
        return yPosStart;
    }
    public Position getDest()
    {
        return dest;
    }
}
