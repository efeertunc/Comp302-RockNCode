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

        avatarHit.setFramePerImage(6);
        avatarHit.addToAlbum(28);
        avatarHit.addToAlbum(5);
        avatarHit.addToAlbum(28);
        avatarHit.addToAlbum(5);
        instance.animationList.add(avatarHit); //INDEX 0


        Animation avatarHitVest = new Animation();

        avatarHitVest.setFramePerImage(4);
        avatarHitVest.addToAlbum(29);
        avatarHitVest.addToAlbum(5);
        avatarHitVest.addToAlbum(29);
        avatarHitVest.addToAlbum(5);
        instance.animationList.add(avatarHitVest); //INDEX 1
    }
    public ArrayList<Animation> getGameAnimations()
    {
        return instance.animationList;
    }


}
