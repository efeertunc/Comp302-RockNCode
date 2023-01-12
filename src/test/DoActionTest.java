package test;

import domain.building.BuildingTracker;
import domain.gameObjects.ObjectTile;
import domain.gameObjects.avatar.Avatar;
import domain.gameObjects.avatar.Bag;
import domain.gameObjects.powerUps.CollectablePowerUpI;
import domain.gameObjects.powerUps.PowerUpTypes;
import domain.gameObjects.powerUps.bottle.BottleState;
import domain.gameObjects.powerUps.bottle.HoldBottle;
import domain.gameObjects.powerUps.bottle.HoldNothing;
import domain.gameObjects.powerUps.protectVest.HasNoVest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

public class DoActionTest {

    Avatar avatar;
    ObjectTile map[][];
    Bag bag;
    BuildingTracker buildingTracker = new BuildingTracker();


    @BeforeEach
    void create() {
        map = new ObjectTile[12][17];
        avatar = new Avatar(2,10,10,10,3);
        bag = avatar.getBag();
    }


    @Test
    @DisplayName("do not changes bottle state when bottle is not in bag")
    void doActionTest1() {
        BottleState firstState = avatar.getBottleState();
        avatar.doAction(KeyEvent.VK_B);
        assertEquals(avatar.getBottleState(), firstState);
    }


    @Test
    @DisplayName("changes bottle state when bottle is in bag")
    void doActionTest2() {
        map = new ObjectTile[12][17];
        map[10][10] = avatar;
        BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).setMap(map);
        BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).setAvatar();
        avatar.getBag().addPowerUp(PowerUpTypes.BOTTLE);
        avatar.doAction(KeyEvent.VK_B);
        assertTrue(avatar.getBottleState() instanceof HoldBottle);
    }


    @Test
    @DisplayName("do not changes anything when key event is not from the accepted keys")
    void doActionTest3() {
        int oldNumBottles = ((CollectablePowerUpI) avatar.getBag().getPower(PowerUpTypes.BOTTLE)).getNum();
        int oldNumVests = ((CollectablePowerUpI) avatar.getBag().getPower(PowerUpTypes.VEST)).getNum();
        int oldNumHints = ((CollectablePowerUpI) avatar.getBag().getPower(PowerUpTypes.HINT)).getNum();
        avatar.doAction(KeyEvent.VK_K);
        assertTrue(avatar.getBottleState() instanceof HoldNothing);
        assertTrue(avatar.getVestState() instanceof HasNoVest);
        assertEquals(oldNumHints, ((CollectablePowerUpI) avatar.getBag().getPower(PowerUpTypes.HINT)).getNum());
        assertEquals(oldNumBottles, ((CollectablePowerUpI) avatar.getBag().getPower(PowerUpTypes.BOTTLE)).getNum());
        assertEquals(oldNumVests, ((CollectablePowerUpI) avatar.getBag().getPower(PowerUpTypes.VEST)).getNum());
    }


    @Test
    @DisplayName("changes number of hints when key event is H and there is a hint in bag")
    void doActionTest4() {
        avatar.getBag().addPowerUp(PowerUpTypes.HINT);
        int oldHintNum = ((CollectablePowerUpI) avatar.getBag().getPower(PowerUpTypes.HINT)).getNum();
        avatar.doAction(KeyEvent.VK_H);
        assertEquals(oldHintNum, ((CollectablePowerUpI) avatar.getBag().getPower(PowerUpTypes.HINT)).getNum() + 1);
    }


    @Test
    @DisplayName("changes vest time of avatar when key event is P and there is a vest in bag")
    void doActionTest5() {
        map = new ObjectTile[12][17];
        map[10][10] = avatar;
        BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).setMap(map);
        BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).setAvatar();
        avatar.getBag().addPowerUp(PowerUpTypes.VEST);
        int oldvestTime = avatar.getVestTime();
        avatar.doAction(KeyEvent.VK_P);
        assertEquals(oldvestTime + 20, avatar.getVestTime());

    }

}
