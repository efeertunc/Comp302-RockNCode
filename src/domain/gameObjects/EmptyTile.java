package domain.gameObjects;

import helperComponents.Position;

public class EmptyTile extends ObjectTile {


    public EmptyTile(int x, int y, int image)
    {
        setPosition(new Position());
        getPosition().setPos(x,y);
        setImage(image);
    }
}
