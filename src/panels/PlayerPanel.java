package panels;

import domain.building.BuildingTracker;
import domain.gameObjects.avatar.Avatar;
import domain.gameObjects.avatar.AvatarInfoObserver;
import domain.gameObjects.avatar.Bag;
import domain.gameObjects.powerUps.PowerUpTypes;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PlayerPanel extends JPanel implements AvatarInfoObserver {

    private JLabel labelTimer;
    private JLabel labelHintNum;
    private JLabel labelBottleNum;
    private JLabel labelLifeNum;
    private JLabel labelVestNum;
    private JLabel keyLabel;
    private Font font1 = new Font("Arial", Font.PLAIN, 15);


    public PlayerPanel() {
        BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar().subscribeAvatarInfoObserver(this);
        design();
        Avatar avatar = BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar();
        updateLife_inPlayerPanel(avatar.getLife());
        Bag bag = avatar.getBag();
        updateBag_inPlayerPanel(bag.getPowerNum(PowerUpTypes.BOTTLE), bag.getPowerNum(PowerUpTypes.HINT), bag.getPowerNum(PowerUpTypes.VEST));
        updateKeyInfo_inPlayerPanel(avatar.isHasKey());
    }


    @Override
    public void updateLife_inPlayerPanel(int avatarLife) {
        labelLifeNum.setText(String.valueOf(avatarLife));
    }

    @Override
    public void updateBag_inPlayerPanel(int numBottle, int numHint, int numVest) {
        labelBottleNum.setText(String.valueOf(numBottle));
        labelHintNum.setText(String.valueOf(numHint));
        labelVestNum.setText(String.valueOf(numVest));
    }

    @Override
    public void updateKeyInfo_inPlayerPanel(boolean hasKey) {
        keyLabel.setVisible(hasKey);
    }


    public void design() {
        setLayout(null);
        setBorder(new LineBorder(new Color(65, 238, 67)));
        setBounds(910, 70, 380, 630);

        // timer
        labelTimer = new JLabel("");
        labelTimer.setBounds(228, 27, 61, 16);
        labelTimer.setHorizontalAlignment(JLabel.CENTER);
        labelTimer.setFont(font1);
        add(labelTimer);


        // life
        Icon imgLife = new ImageIcon(this.getClass().getResource("/visual/life.png"));
        JLabel labelLifeIMG = new JLabel(imgLife);
        labelLifeIMG.setBounds(27, 27, 16, 16); // You can use your own values
        add(labelLifeIMG);

        labelLifeNum = new JLabel();
        labelLifeNum.setBounds(27, 27, 61, 16);
        labelLifeNum.setHorizontalAlignment(JLabel.CENTER);
        labelLifeNum.setFont(font1);
        add(labelLifeNum);


        // bottle
        JLabel bottleIMG = new JLabel(new ImageIcon(this.getClass().getResource("/visual/bottle.png")));
        bottleIMG.setBounds(27, 66, 16, 16);
        add(bottleIMG);

        labelBottleNum = new JLabel("");
        labelBottleNum.setBounds(27, 66, 61, 16);
        labelBottleNum.setHorizontalAlignment(JLabel.CENTER);
        labelBottleNum.setFont(font1);
        labelBottleNum.setText(Integer.toString(3));
        add(labelBottleNum);


        // vest
        JLabel vestImg = new JLabel(new ImageIcon(this.getClass().getResource("/visual/vest.png")));
        vestImg.setBounds(112, 27, 16, 16); // You can use your own values
        add(vestImg);

        labelVestNum = new JLabel("");
        labelVestNum.setBounds(112, 27, 61, 16);
        labelVestNum.setHorizontalAlignment(JLabel.CENTER);
        labelVestNum.setFont(font1);
        add(labelVestNum);


        // hint
        JLabel hintImg = new JLabel(new ImageIcon(this.getClass().getResource("/visual/quest.png")));
        hintImg.setBounds(112, 66, 16, 16); // You can use your own values
        add(hintImg);

        labelHintNum = new JLabel("");
        labelHintNum.setBounds(112, 66, 61, 16);
        labelHintNum.setHorizontalAlignment(JLabel.CENTER);
        labelHintNum.setFont(font1);
        add(labelHintNum);


        // key
        keyLabel = new JLabel(new ImageIcon(this.getClass().getResource("/visual/keyFound.gif")));
        keyLabel.setBounds(100, 250, 100, 300); // You can use your own values
        add(keyLabel);

        setVisible(true);
    }

    public JLabel getLabelTimer() {
        return labelTimer;
    }

    public void setLabelTimer(JLabel labelTimer) {
        this.labelTimer = labelTimer;
    }
}
