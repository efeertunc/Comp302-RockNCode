package domain.gameObjects;

import com.google.api.Endpoint;
import com.google.cloud.storage.Acl;
import helperComponents.Position;

import javax.xml.stream.events.EndDocument;

public class Projectile {
    private int image;
    private double speed;

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
    public Projectile(Position startPoint, Position endPoint, float speed, int image)
    {
        this.image= image;
        this.speed = speed;
        xPosStart = parseX(startPoint.getX());
        yPosStart = parseY(startPoint.getY());
        xPosEnd = parseX(endPoint.getX());
        yPosEnd = parseY(endPoint.getY());
        xVector = xPosEnd - xPosStart;
        yVector = yPosEnd - yPosStart;
        double magnitude = Math.sqrt(Math.pow(xVector, 2) + Math.pow(yVector, 2));
        int totalFrame = (int) (magnitude / speed);
        xSpeed = totalFrame / xVector;
        ySpeed = totalFrame / yVector;
    }

    public int getImage()
    {
        return image;
    }

    public double getSpeed()
    {
        return speed;
    }

    public boolean move() {
        if (counter < totalFrame)
        {
            xPosStart = xPosStart + xSpeed;
            yPosStart = yPosStart + ySpeed;
            counter +=1;
            return true;
        }
        else
        {
            return false;
        }
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
}
