package test;

import domain.building.Building;
import domain.building.BuildingType;
import domain.gameObjects.ObjectTile;
import domain.gameObjects.avatar.Avatar;
import domain.gameObjects.obstacle.Obstacle;
import helperComponents.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import panels.BuildingMap;

import javax.swing.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class searchKeyTest {

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
        map1[0][0] = new Obstacle(0,0,0,0); //obstacle added
        map1[1][1] = new Obstacle(0,0,0,0); //obstacle added
        building = new Building(map, BuildingType.CASE,3);
        building.setMap(map1);
        ((Obstacle)building.getMap_obj()[0][0]).generateKey(0); //obstacle marked with key
    }


    @DisplayName("searchKey throws IllegalArgumentException when y < 0 or x < 0 ")
    @Test
    void Test1() {
        x = -10;
        y = -10;
        avatar.setPosition(new Position(5,5));
        assertThrows(IllegalArgumentException.class,
                () -> {
                    avatar.searchKey(x,y, building);
                });
    }

    @DisplayName("searchKey throws null when input is far away from the avatar")
    @Test
    void Test2() {
        x = 0;
        y = 0;
        avatar.setPosition(new Position(5,5));
        assertThrows(NullPointerException.class,
                () -> {
                    avatar.searchKey(x, y, building);
                });
    }

    @DisplayName("searchKey throws null when there is no obstacle in map[y][x]")
    @Test
    void Test3() {
        x = 4;
        y = 5;
        avatar.setPosition(new Position(5,5));
        assertThrows(NullPointerException.class,
                () -> {
                    avatar.searchKey(x, y, building);
                });
    }

    @DisplayName("searchKey throws false when there is no key in the obstacle which is in map[y][x]")
    @Test
    void Test4() {
        x = 1;
        y = 1;
        avatar.setPosition(new Position(1,2));
        assertFalse(avatar.searchKey(x, y, building));
    }

    @DisplayName("searchKey throws true when there is a key in the obstacle which is in map[y][x]")
    @Test
    void Test5() {
        x = 0;
        y = 0;
        avatar.setPosition(new Position(0,1));
        assertTrue(avatar.searchKey(x, y, building));
    }







}