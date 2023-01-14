package helperComponents;

import main.EscapeFromKoc;

import java.util.ArrayList;

public class AnimationTracker {

    private static AnimationTracker instance;

    private ArrayList<Animation> animationList;
    private AnimationTracker()
    {
    }


    public static AnimationTracker getInstance() {
        if (instance == null) {
            instance = new AnimationTracker();
            instance.animationList = new ArrayList<Animation>();
            instance.setGameAnimations();
        }
        return instance;
    }

    private void setGameAnimations(){
        Animation avatarHit = new Animation();

        avatarHit.setFramePerImage(3);

        avatarHit.addToAlbum(9);
        avatarHit.addToAlbum(10);
        avatarHit.addToAlbum(11);
        avatarHit.addToAlbum(12);
        avatarHit.addToAlbum(13);
        avatarHit.addToAlbum(14);
        instance.animationList.add(avatarHit); //INDEX 0
    }
    public ArrayList<Animation> getGameAnimations()
    {
        return instance.animationList;
    }


}
