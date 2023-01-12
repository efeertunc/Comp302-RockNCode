package test;

import domain.building.BuildingTracker;
import domain.gameObjects.EmptyTile;
import domain.gameObjects.ObjectTile;
import domain.gameObjects.avatar.Avatar;
import domain.gameObjects.avatar.Bag;
import domain.gameObjects.powerUps.PowerUpTypes;
import helperComponents.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AvatarTest {
    Avatar avatar;
    ObjectTile map[][];
    Bag bag;



    //BuildController buildController = new BuildController();
    BuildingTracker buildingTracker = new BuildingTracker();
    @BeforeEach
    void create() {
        map = new ObjectTile[12][17];
        avatar = new Avatar(2,10,10,10,3);
        bag = avatar.getBag();

    }

    @Test
    void throwBottleTest() {
        assertEquals(avatar.throwBottle(Direction.fourDir.right), "Bottle has been thrown right");
        assertEquals(avatar.throwBottle(Direction.fourDir.left), "Bottle has been thrown left");
        assertEquals(avatar.throwBottle(Direction.fourDir.down), "Bottle has been thrown down");
        assertEquals(avatar.throwBottle(Direction.fourDir.up), "Bottle has been thrown up");
    }
    @Test
    void changeVestStateTest() {
        assertFalse(avatar.changeVestState());
        assertTrue(avatar.repOk());
        avatar.getBag().addPowerUp(PowerUpTypes.VEST);
        assertTrue(avatar.changeVestState());
        assertTrue(avatar.repOk());
        assertFalse(avatar.changeVestState());

    }

    @Test
    void changeBottleStateTest() {
        assertFalse(avatar.changeBottleState());
        assertTrue(avatar.repOk());
        avatar.getBag().addPowerUp(PowerUpTypes.BOTTLE);
        assertTrue(avatar.changeBottleState());
        assertTrue(avatar.repOk());
        assertFalse(avatar.changeBottleState());
    }

    @Test
    void moveTest() {
        avatar.getPosition().setPos(20,20);
        assertThrows(NullPointerException.class,
                () -> {
                    avatar.move(Direction.fourDir.up);
                });

        avatar.getPosition().setPos(10,20);
        assertThrows(NullPointerException.class,
                () -> {
                    avatar.move(Direction.fourDir.up);
                });

        avatar.getPosition().setPos(20,10);
        assertThrows(NullPointerException.class,
                () -> {
                    avatar.move(Direction.fourDir.up);
                });


    }
    @Test
    void moveTest2() {
        for(int i = 0 ; i<12 ; i++){
            for(int j =0; j<17 ; j++){
                map[i][j] = new EmptyTile(i,j,4);
            }
        }
        buildingTracker.getBuildingList().get(0).setMap(map);
        avatar.getPosition().setPos(10,10);
        assertEquals(avatar.move(Direction.fourDir.up), "Updated position  x: 10 y: 9");

        avatar.getPosition().setPos(10,10);
        assertEquals(avatar.move(Direction.fourDir.down), "Updated position  x: 10 y: 11");
        assertTrue(avatar.repOk());

        avatar.getPosition().setPos(10,10);
        assertEquals(avatar.move(Direction.fourDir.right), "Updated position  x: 11 y: 10");
        assertTrue(avatar.repOk());
        assertEquals(avatar.move(Direction.fourDir.right), "Updated position  x: 12 y: 10");

    }


}
