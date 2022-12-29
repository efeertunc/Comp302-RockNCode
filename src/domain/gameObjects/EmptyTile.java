package domain.gameObjects;

import helperComponents.Position;

public class EmptyTile extends ObjectTile {


    public EmptyTile(int x, int y, int image)
    {
        this.position = new Position();
        position.setPos(x,y);
        this.image = image;
    }
}
