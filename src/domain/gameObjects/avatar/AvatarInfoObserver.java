package domain.gameObjects.avatar;

public interface AvatarInfoObserver {

    void updateLife_inPlayerPanel(int life);

    void updateBag_inPlayerPanel();

    void updateScore_inPlayerPanel();
}
