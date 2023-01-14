package helperComponents;

import domain.gameObjects.DynamicTile;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Animator {

    private DynamicTile holderObject;
    private ArrayList<Animation> animations;
    private int activeAnim = -1;
    private int baseImage;


    public Animator(DynamicTile holderObj)
    {
        animations = new ArrayList<Animation>();
        this.holderObject = holderObj;
        baseImage = holderObj.getImage();
    }

    public DynamicTile getHolderObj()
    {
        return holderObject;
    }
    public int getBaseImage()
    {
        return baseImage;
    }
    public void setBaseImage(int image)
    {
        baseImage = image;
    }

    public void addAnimation(Animation animation)
    {
        animation.setHolderAnimator(this);
        animations.add(animation);
    }

    public void setState(int index) //-1 for null animation
    {
        activeAnim = index;
        if (activeAnim == -1)
        {
            clearAllAnimations();
        }
        else
        {
            activateAnimation(activeAnim);
        }
    }



    private void activateAnimation(int index)
    {
        clearAllAnimations();
        animations.get(index).activateAnimation();
    }

    private void clearAllAnimations()
    {
        for (int i = 0; i<animations.size();i++)
        {
            animations.get(i).resetAnimation();
        }
    }

    public void animatorUpdate()
    {
        if (activeAnim == -1)
        {
            //pass
        }
        else
        {
            animations.get(activeAnim).animationUpdate();
        }
    }
}
