package helperComponents;

import java.util.ArrayList;

public class Animation {


    private Animator holderAnimator;
    private ArrayList<Integer> album;
    private int framePerImage = 1;
    ////////////////

    private boolean isPlaying;
    private int currentIndex;
    private int frameCounter;

    public Animation()
    {
        album = new ArrayList<Integer>();
    }

    public void setHolderAnimator(Animator holder)
    {
        this.holderAnimator = holder;
    }

    public void addToAlbum(int image)
    {
        album.add(image);
    }

    public void activateAnimation()
    {
        holderAnimator.setBaseImage(holderAnimator.getHolderObj().getImage());
        isPlaying= true;
    }
    public int getFramePerImage()
    {
        return framePerImage;
    }

    public void setFramePerImage(int value)
    {
        this.framePerImage = value;
    }
    public void resetAnimation()
    {
        if (isPlaying)
        {
            isPlaying=false;
            currentIndex = 0;
            frameCounter = 0;
            holderAnimator.getHolderObj().setImage(holderAnimator.getBaseImage());
        }

    }

    public void animationUpdate()
    {
        if (isPlaying) {
            if (currentIndex == album.size() - 1) {
                endAnimation();
                return;
            }
            holderAnimator.getHolderObj().setImage(album.get(currentIndex));
            if (frameCounter == framePerImage) {
                currentIndex += 1;
                frameCounter = 0;
                return;
            }

            frameCounter += 1;
        }
    }

    private void endAnimation()
    {
        resetAnimation();
        holderAnimator.setState(-1);
    }
}
