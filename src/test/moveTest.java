package test;

import domain.building.BuildingType;
import domain.gameObjects.EmptyTile;
import domain.gameObjects.ObjectTile;
import domain.gameObjects.obstacle.Obstacle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import domain.building.Building;
import panels.BuildingMap;
import domain.gameObjects.avatar.Avatar;

import javax.swing.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class moveTest {

    JPanel panel= new JPanel();
    BuildingMap buildingMap= new BuildingMap(panel);
    int[][] map;
    ObjectTile map1[][];
    Building building;
    int life;
    int time;
    int x;
    int y;
    int image;
    Avatar avatar = new Avatar(life, time, x, y, image);
    @BeforeEach
    void create() {
        map1 = new ObjectTile[12][17];
        Building building = new Building(map, BuildingType.CASE,3);
        building.setMap(map1);
    }
    @DisplayName("move throws IllegalArgumentException when y < 0 or x < 0 ")
    @Test
        //in this test if x or y is negative number; it throws exception
    void Test1() {
        x = -10;
        y = -10;
        assertThrows(IllegalArgumentException.class,
                () -> {
                    avatar.move(x,y, building);
                });
    }

    @DisplayName("move throws IllegalArgumentException when x >= 17 and y >= 12")
    @Test
    void Test2() {
        //in this test if x or y is bigger than the boundaries; it throws exception
        x = 13;
        y = 18;
        assertThrows(IllegalArgumentException.class,
                () -> {
                    avatar.move(x, y, building);
                });
    }
   // @Test
    //void Test3() {
        //if there is no obstacle in given place in the map
        //then move method should return true
      //  map1[2][2] = new EmptyTile(2,2,1);
        //assertTrue(avatar.move(2,2,building));
        //assertTrue(building.getMap_obj()[2][2] instanceof Avatar);
    //}
   //@Test
    //void Test4() {
        //if there is an obstacle in given place in the map
        //then move method should return false
      //  map1[1][1] = new Obstacle(1,1,1,1);
        //assertFalse(avatar.move(1,1,building));
    //}
    @Test
    void Test5() {
        //if given place is null in the map
        //then move method should return false
        //boolean tester = avatar.move(2,2,building);
        assertFalse(building.getMap_obj()[2][2] instanceof Avatar);
    }
}