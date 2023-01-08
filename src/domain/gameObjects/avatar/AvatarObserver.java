package domain.gameObjects.avatar;

public interface AvatarObserver {

    void updateLife_inPlayerPanel(int life);

    void updateBag_inPlayerPanel();

    void updateScore_inPlayerPanel();
}
