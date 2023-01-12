package domain.gameObjects.alien;


import domain.SoundManager;
import domain.gameObjects.DynamicTile;
import helperComponents.Position;

public abstract class Alien extends DynamicTile {


    private SoundManager sound;
    public Alien(int x, int y, int image) {
        sound = new SoundManager();
        setPosition(new Position(x,y));
        setImage(image);
    }

    public SoundManager getSound()
    {
        return sound;
    }
}