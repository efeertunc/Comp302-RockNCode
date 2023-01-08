package panels;

import domain.building.BuildingTracker;
import domain.gameObjects.avatar.AvatarObserver;

import javax.swing.*;

public class PlayerPanel extends JPanel implements AvatarObserver {


    public PlayerPanel() {
        BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar().setAvatarObserver(this);
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
