package panels;

import domain.TileManager;
import domain.gameObjects.ObjectTile;
import domain.gameObjects.obstacle.Obstacle;
import factory.PanelType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import views.GameView;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class AddToMapTest {
    TileManager tm= new TileManager();
    JPanel panel= new JPanel();
    BuildingMap buildingMap= new BuildingMap(panel);

    @BeforeEach
    void init() {
        buildingMap.setMap(buildingMap.initial_map());
    }
    @Test
    void test1() {
        //This function adds the object into the map by parsing the x and y values.
        //In this test, we initialized the map as empty and then sent feasible inputs to place an object.

       int[]a= new int[3];
       a[0]=6;
       a[1]=11;
       a[2]=3;
        assertArrayEquals(buildingMap.addToMap ( 310,550,3),a);
    }
    @Test
    void test2() {
        //In this test, the place is not empty, so the adding will fail.

        buildingMap.addToMap ( 290,500,3);
        Assertions.assertEquals(buildingMap.addToMap ( 300,510,3),null);

    }
    @Test
    void test3() {
        //In this test, the given x value is out of bound,so the method returns null.

        Assertions.assertEquals(buildingMap.addToMap ( 900,510,3),null);
    }
    @Test
    void test4() {
        //In this test, the given y value is out of bound,so the method returns null.

        Assertions.assertEquals(buildingMap.addToMap ( 300,1000,3),null);
    }
    @Test
    void test5() {
        //In this test, the given type value is out of bound,so the method returns null.

        Assertions.assertEquals(buildingMap.addToMap ( 65,60,19),null);
    }
}