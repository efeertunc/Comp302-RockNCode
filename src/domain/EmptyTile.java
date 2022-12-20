package domain;

import HelperComponents.Position;
import objects.ObjectTile;

import java.awt.image.BufferedImage;

public class EmptyTile extends ObjectTile {


    public EmptyTile(int x, int y, int image)
    {
        this.position = new Position();
        position.setPos(x,y);
        this.image = image;
    }
}
