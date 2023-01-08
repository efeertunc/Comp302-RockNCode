package test;

import domain.building.BuildingType;
import domain.gameObjects.EmptyTile;
import domain.gameObjects.ObjectTile;
import domain.gameObjects.obstacle.Obstacle;
import org.junit.jupiter.api.BeforeEach;
import domain.building.Building;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import panels.BuildingMap;
import domain.gameObjects.avatar.Avatar;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class updatePositionTest {

    int[][] map1;
    ObjectTile map[][];
    Building building;
    int life;
    int time;
    int x;
    int y;
    int image;
    Avatar avatar = new Avatar(life, time, x, y, image);

    @BeforeEach
    void create() {
        map = new ObjectTile[12][17];
        building = new Building(map1, BuildingType.CASE, 3);
        building.setMap(map);
        for(int i = 0 ; i<12 ; i++){
            for(int j =0; j<17 ; j++){
                map[i][j] = new EmptyTile(i,j,4);
            }
        }
    }

    @DisplayName("updatePosition throws NullPointerException")
    @Test
        //in this test if x or y is negative number; it throws exception
    void Test1() {
        x = -10;
        y = -10;
        assertThrows(NullPointerException.class,
                () -> {
                    avatar.updatePosition(x, y, building);
                });
        //in this test if x or y is bigger than the boundaries; it throws exception
        x = 13;
        y = 18;
        assertThrows(NullPointerException.class,
                () -> {
                    avatar.updatePosition(x, y, building);
                });
    }

    @Test
    void Test2() {
        //if given place is emptyTile in the map
        //then avatar will be moved to there
        assertTrue(avatar.updatePosition(1,1,building));
        assertFalse(building.getMap_obj()[2][2] instanceof Avatar);
        assertTrue(building.getMap_obj()[1][1] instanceof Avatar);
        avatar.getPosition().setPos(1,1);
        assertTrue(avatar.updatePosition(2,1,building));
        assertTrue(building.getMap_obj()[1][2] instanceof Avatar);
    }

    @Test
    void Test3() {
        //if there is no obstacle in given place in the map
        //then update position method should return true
        assertTrue(map[10][10] instanceof EmptyTile);
        assertTrue(avatar.updatePosition(10,10,building));
        assertTrue(building.getMap_obj()[10][10] instanceof Avatar);
    }

    @Test
    void Test4() {
        //if there is an obstacle in given place in the map
        //then move method should return false
        map[1][1] = new Obstacle(1,1,1,1);
        assertFalse(avatar.updatePosition(1,1,building));
    }
    @Test
    void Test5() {
        //returns true if place is empty
        //moves the avatar given place checks one more time if it is moved

        assertTrue(map[10][10] instanceof EmptyTile);
        assertTrue(avatar.updatePosition(10,10,building));
        assertTrue(building.getMap_obj()[10][10] instanceof Avatar);
        assertTrue(map[9][9] instanceof EmptyTile);
        assertFalse(building.getMap_obj()[9][9] instanceof Avatar);
    }




}