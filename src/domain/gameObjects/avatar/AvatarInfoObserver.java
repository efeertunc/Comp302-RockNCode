package domain.gameObjects.avatar;

public interface AvatarInfoObserver {

    void updateLife_inPlayerPanel(int life);

    void updateBag_inPlayerPanel(int numBottle, int numHint, int numVest);

    void updateKeyInfo_inPlayerPanel(boolean hasKey);

}
