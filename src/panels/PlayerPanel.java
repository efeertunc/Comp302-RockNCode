package panels;

import domain.building.BuildingTracker;
import domain.gameObjects.avatar.AvatarInfoObserver;

import javax.swing.*;

public class PlayerPanel extends JPanel implements AvatarInfoObserver {


    public PlayerPanel() {
        BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar().subscribeAvatarInfoObserver(this);
    }



    @Override
    public void updateLife_inPlayerPanel(int avatarLife) {

    }

    @Override
    public void updateBag_inPlayerPanel() {

    }

    @Override
    public void updateScore_inPlayerPanel() {

    }
}
