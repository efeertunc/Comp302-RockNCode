package panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import models.Constants;
import domain.building.BuildingTracker;
import domain.gameObjects.EmptyTile;
import domain.gameObjects.obstacle.Obstacle;
import main.IPanel;
import domain.gameObjects.ObjectTile;

import static models.Constants.*;

public class BuildingMap extends JPanel {
    JPanel panel;
    private ObjectTile[][] map;
   static int[][][] tileMap = new int[13][18][2];
    BufferedReader br;

    public BuildingMap(JPanel panel) {
        this.panel = panel;
        design();
        try {
            openFile(FileConstants.fileList[BuildingTracker.getCurrentIndex()]);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void setMapForDB(){
        map = BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getMap_obj();
    }


    public void showPanel(Boolean show) {
        this.setVisible(show);

    }

    public void design() {
        this.setBackground(Color.PINK);
        this.setLayout(null);
        this.setBorder(new LineBorder(new Color(255, 120, 241)));
        this.setBounds(0, 70, 900, 630);
        panel.add(this);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        draw(g2D);
    }
public void printAll(Graphics2D g2D,int imageId,int i,int j){
    if (imageId == 0){
        g2D.drawImage(ImageConstants.SHELVE, parseX(i), parseY(j), 48 + 5,
                48 + 5, null);
    }
    if (imageId == 1){
        g2D.drawImage(ImageConstants.CHAIR, parseX(i), parseY(j), 48 + 5,
                48 + 5, null);
    }
    if (imageId == 2){
        g2D.drawImage(ImageConstants.BIN, parseX(i), parseY(j), 48 + 5,
                48 + 5, null);
    }
    if (imageId == 3){
        g2D.drawImage(ImageConstants.TABLE, parseX(i), parseY(j), 48 + 5,
                48 + 5, null);
    }
    if (imageId == 4){
        g2D.drawImage(ImageConstants.EMPTY, parseX(i), parseY(j), 48 + 5,
                48 + 5, null);
    }
    if (imageId == 15){
        g2D.drawImage(ImageConstants.OMER, parseX(i), parseY(j), 48 + 5,
                48 + 5, null);
    }if (imageId == 16){
        g2D.drawImage(ImageConstants.CASE, parseX(i), parseY(j), 48 + 5,
                48 + 5, null);
    }
    if (imageId == 17){
        g2D.drawImage(ImageConstants.SOS, parseX(i), parseY(j), 48 + 5,
                48 + 5, null);
    }
    if (imageId == 18){
        g2D.drawImage(ImageConstants.SCIE, parseX(i), parseY(j), 48 + 5,
                48 + 5, null);
    }
    if (imageId == 19){
        g2D.drawImage(ImageConstants.ENG, parseX(i), parseY(j), 48 + 5,
                48 + 5, null);
    }
    if (imageId == 20){
        g2D.drawImage(ImageConstants.SNA, parseX(i), parseY(j), 48 + 5,
                48 + 5, null);
    }
    if (imageId == 21){
        g2D.drawImage(ImageConstants.STONE, parseX(i), parseY(j), 48 + 5,
                48 + 5, null);
    }
    if (imageId == 22){
        g2D.drawImage(ImageConstants.WOOD, parseX(i), parseY(j), 48 + 5,
                48 + 5, null);
    }
    if (imageId == 23){
        g2D.drawImage(Constants.ImageConstants.POT, parseX(i), parseY(j), 48 + 5,
                48 + 5, null);
    }

}
    public void draw(Graphics2D g2D) {

        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 18; j++) {
                int x = unparseX(i);
                int y = unparseY(j);
                for (int a = 0; a < 2; a++) {
                    int imageId=tileMap[i][j][a];
                    if (imageId != -1) {
                        printAll( g2D,imageId,j, i);
                    }
                }
            }
        }
        g2D.drawImage(ImageConstants.CLOSEDOOR, 810, 470, 150,
                110, null);
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 12; j++) {
                int imageId = map[j][i].getImage();

                if (imageId != -1) {
                    printAll( g2D,imageId,i, j);
                }

            }

        }

    }

    public void updateMap (int x, int y, int type) {
        // eskisi bu bug olursa geri al
        //map[y][x] = new Obstacle(type, x, y, tm.objects[type].getImage());
        map[y][x] = new Obstacle(type, x, y, type);
    }

    public int[] addToMap ( int x, int y, int b){
        //REQUIRES: y<= 550 and x <= 800 and b<=tm.objects.length-1
        //EFFECTS: If x,y and b are in feasible range, add them to lists and update map.
        //MODIFIES: x_list, y_list, objtype and lastly map.

        // number of objtypes
        if (b>12){
            return null;
        }
        int x_reduced = x % 50;
        int x_new;
        if (x > 800) {
            //x_new = 800;
            return null;
        } else {
            if (x_reduced < 25) {
                x_new = x - x_reduced;

            } else {
                x_new = x + (50 - x_reduced);
            }
        }

        int y_reduced = y % 50;
        int y_new;
        if (y > 550) {
            // y_new = 550;
            return null;
        } else {
            if (y_reduced < 25) {
                y_new = y - y_reduced;
            } else {
                y_new = y + (50 - y_reduced);
            }
        }
        // x, y between 0-800 and 0-550
        System.out.printf("x is %d and y is %d", x_new, y_new);
        //Here, we check whether this location is empty or not, by looking at lists
        // For database, we can check in map later.
        if (!inMap(x_new, y_new, map)) {
            updateMap(unparseX(x_new), unparseY(y_new), b);
            repaint();
            int[] a=new int[3];
            a[0]= unparseX(x_new);
            a[1]=unparseY(y_new);
            a[2]= b;
            return a;
        }
        return null;
    }
    public void delete (int x,int y) {
        x=unparseX(x);
        y=unparseY(y);
        map[y][x]= new EmptyTile(x,y,4);
        repaint();

    }
    public void emptyMap () {
        map = initial_map();
        repaint();

    }


    public ObjectTile[][] getMap () {
        return map;
    }

    public int getObjectCount (){
        int count = 0;
        if (map != null){
            for (int i = 0; i < 17; i++) {
                for (int j = 0; j < 12; j++) {
                    if (map[j][i].getImage() == 4){
                        count++;
                    }
                }
            }
        }

        return (17*12) - count;
    }

    private int parseX ( int x)
    {
        return  x * 50;
    }
    private int parseY ( int y)
    {
        return y * 50;
    }
    private int unparseX ( int x)
    {
        return (x / 50);
    }
    private int unparseY ( int y)
    {
        return (y / 50);
    }


    public ObjectTile[][] initial_map () {
        ObjectTile[][] map = new ObjectTile[12][17];
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 17; j++) {
                map[i][j] = new EmptyTile(j, i, 4);
            }
        }
        return map;
    }
    public static void openFile(String filename) throws FileNotFoundException {
        tileMap=new int[13][18][2];
        try {
            Scanner input = new Scanner(Paths.get(filename));
            int i = 0;
            int j = 0;
            while (input.hasNext()) { // while there is more to read
                // display each read word and add it to the ArrayList wordList
                String parses = input.next();

                String[] block = parses.split(",");
                if (block.length == 1) {
                    tileMap[i][j][0] = Integer.parseInt(block[0]);

                    tileMap[i][j][1] = -1;

                } else {
                    tileMap[i][j][0] = Integer.parseInt(block[0]);
                    tileMap[i][j][1] = Integer.parseInt(block[1]);


                }

                j++;

                if (j == 18) {

                    j = 0;
                    i++;

                }
            }

        }

        // IOException
        catch (IOException io) {
            System.err.println("Error opening input file. Terminating.");
            System.exit(1);
        }

    }

    public boolean inMap(int x, int y,ObjectTile[][] map) {
        return map[unparseY(y)][unparseX(x)].getImage() != 4;
    }

    public void setMap(ObjectTile[][] map) {
        this.map = map;
    }
}