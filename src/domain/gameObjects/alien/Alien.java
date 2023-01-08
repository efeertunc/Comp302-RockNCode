package domain.gameObjects.alien;


import domain.gameObjects.DynamicTile;
import helperComponents.Position;

public abstract class Alien extends DynamicTile {


    public Alien(int x, int y, int image) {
        setPosition(new Position(x,y));
        setImage(image);
    }

}