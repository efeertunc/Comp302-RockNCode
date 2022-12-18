package domain;

import HelperComponents.Position;
import objects.ObjectTile;

import java.awt.image.BufferedImage;

public abstract class Alien extends ObjectTile {



    public abstract void Update(Building building, double intervalTime);
}
