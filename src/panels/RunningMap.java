package panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.LineBorder;

import models.Constants;
import domain.*;
import domain.building.BuildingTracker;
import domain.gameObjects.DynamicTile;
import domain.gameObjects.ObjectTile;

public class RunningMap extends JPanel implements Runnable {

    private ObjectTile[][] map_obj;
    int FPS = 60;
    static int[][][] tileMap = new int[13][18][2];
    public boolean isPaused;
    private int originalTileSize = 50; // 48x48 tile
    private double scale = 1;

    public double getScale() {
        return scale;
    }

    public void decreaseScale() {
        this.scale = scale*0.95;
    }
    public void increasecale() {
        this.scale = scale*1.05;
    }
    JPanel panel;

    RunPanel superPanel;
    Point startPoint;
    Thread thread;
    AlienGenerator generator;  //TEST PURPOSES



    public ObjectTile[][] getMap_obj() {
        return map_obj;
    }

    public void setMap_obj(ObjectTile[][] map_obj) {
        this.map_obj = map_obj;
    }


    public RunningMap(JPanel panel, RunPanel _panel) {
        this.superPanel=_panel;
        this.panel = panel;
        initialize();
        map_obj = BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getMap_obj();
        //initialize();
        design();
    }


    public void showPanel(Boolean show) {
        this.setVisible(show);
    }


    public void initialize() {
        try {
            openFile(Constants.FileConstants.fileList[BuildingTracker.getCurrentIndex()]);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        generator = new AlienGenerator();
    }

    private int parseX(int x)
    {
        return (int)(x*50*scale);
    }
    private int parseY(int y)
    {
        return (int)(y*50*scale);


    }

    public void design() {

        this.setLayout(null);
        this.setBorder(new LineBorder(new Color(255, 120, 241)));
        this.setBounds(0, 70, 900, 630);
        panel.add(this);


    }


    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        draw(g2D);
    }
    public void printAll(Graphics2D g2D,int imageId,int i,int j){
        int weight;
        weight=(int) (originalTileSize * scale);
        int x=parseX(i);
        int y= parseY(j);
        if (imageId == 0) {
            g2D.drawImage(Constants.ImageConstants.SHELVE, x,y, weight,weight, null);
        }
        if (imageId == 1) {
            g2D.drawImage(Constants.ImageConstants.CHAIR, x,y, weight,weight, null);
        }
        if (imageId == 2) {
            g2D.drawImage(Constants.ImageConstants.BIN,  x,y, weight,weight, null);
        }
        if (imageId == 3) {
            g2D.drawImage(Constants.ImageConstants.TABLE,  x,y, weight,weight, null);
        }
        if (imageId == 4) {
            g2D.drawImage(Constants.ImageConstants.EMPTY,  x,y, weight,weight, null);
        }
        if (imageId == 5) {

            g2D.drawImage(Constants.ImageConstants.AVATAR,  x,y, weight,weight,null);
            g2D.drawImage(Constants.ImageConstants.CLOSEDOOR, (int)(810*scale), (int)(470*scale),(int)(150*scale),
                    (int)(110*scale), null);
        }
        if (imageId == 6) {
            g2D.drawImage(Constants.ImageConstants.AVATAR_HAPPY,  x,y, weight,weight, null);
            g2D.drawImage(Constants.ImageConstants.OPENDOOR, (int)(810*scale), (int)(470*scale),(int)(110*scale),
                    (int)(110*scale), null);


        }
        if (imageId == 7) {
            g2D.drawImage(Constants.ImageConstants.ALIEN_TIMEWASTER, x,y, weight,weight, null);
        }
        if (imageId == 8) {
            g2D.drawImage(Constants.ImageConstants.KEY, x,y, weight,weight,null);
            g2D.drawImage(Constants.ImageConstants.KEY, x,y, weight,weight,null);
        }
        if (imageId == 9) {
            g2D.drawImage(Constants.ImageConstants.ALIEN_SHOOTER, x,y, weight,weight,null);
        }
        if (imageId == 10){
            g2D.drawImage(Constants.ImageConstants.ALIEN_BLIND, x,y, weight,weight,null);
        }
        if (imageId == 11){
            g2D.drawImage(Constants.ImageConstants.EXTRATIME, x,y, weight,weight,null);
        }
        if (imageId == 12){
            g2D.drawImage(Constants.ImageConstants.EXTRALIFE, x,y, weight,weight,null);
        }
        if (imageId == 13){
            g2D.drawImage(Constants.ImageConstants.ALIEN_SHOOTER_ATTACK, x,y, weight,weight, null);
        }
        if (imageId == 14){
            g2D.drawImage(Constants.ImageConstants.ALIEN_BLIND_ATTACK, x,y, weight,weight, null);
        }
        if (imageId == 15){
            g2D.drawImage(Constants.ImageConstants.OMER, x,y, weight,weight,null);
        }if (imageId == 16){
            g2D.drawImage(Constants.ImageConstants.CASE, x,y, weight,weight,null);
        }
        if (imageId == 17){
            g2D.drawImage(Constants.ImageConstants.SOS, x,y, weight,weight,null);
        }
        if (imageId == 18){
            g2D.drawImage(Constants.ImageConstants.SCIE, x,y, weight,weight,null);
        }
        if (imageId == 19){
            g2D.drawImage(Constants.ImageConstants.ENG, x,y, weight,weight,null);
        }
        if (imageId == 20){
            g2D.drawImage(Constants.ImageConstants.SNA,x,y, weight,weight,null);
        }
        if (imageId == 21){
            g2D.drawImage(Constants.ImageConstants.STONE, x,y, weight,weight,null);
        }
        if (imageId == 22){
            g2D.drawImage(Constants.ImageConstants.WOOD,x,y, weight,weight,null);
        }
        if (imageId == 23){
            g2D.drawImage(Constants.ImageConstants.POT,x,y, (int)(weight*2),(int)(weight*2),null);
        }
        if (imageId == 24){
            g2D.drawImage(Constants.ImageConstants.ENTER,x,y, (int)(weight*0.6),(int)(weight*0.6),null);
        }

    }
    public void draw(Graphics2D g2D) {


        if (thread.isAlive())
        {
            for (int i = 0; i < 13; i++) {
                for (int j = 0; j < 18; j++) {
                    int x = parseX(i);
                    int y = parseY(j);
                    for (int a = 0; a < 2; a++) {
                        int imageId=tileMap[i][j][a];
                        if (imageId != -1) {
                            printAll( g2D,imageId,j, i);
                        }
                    }
                }
            }
        for (int i = 0 ; i< 17; i ++) {
            for (int j = 0; j < 12; j++) {
                if (map_obj[j][i] == null){
                    System.out.println("i" + i + "j" + j);
                    continue;
                }
                //System.out.println("j: " + j + " i: " + i + " map_obj: " + map_obj[j][i].image);
                int imageId = map_obj[j][i].getImage();
                //g2D.drawImage(Constants.ImageConstants.CHAIR, parseX(5), parseY(5), 48 + 5,48 + 5, null);
                if (imageId != -1) {
                    printAll(g2D,imageId, i,j);


                }
            }

        }

        }
    }


    public void startThread()
    {
        thread = new Thread(this);
        thread.start();
    }


    @Override
    public void run() {

        BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).generateAlien();

        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (thread != null) {

            update(drawInterval);
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(double intervalTime) {
        if(!isPaused) {
            superPanel.countdown();
            int oldTime = ((int) BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getPowerUpTime());
            BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).updateTime(intervalTime);
            BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).updatePowerUpTime(intervalTime);
            int powerUpTime = ((int) BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getPowerUpTime());
            if ((powerUpTime % 12 == 0) && (oldTime!= powerUpTime)) {
                BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).generatePowerTile();
            }
            for (int i = 0; i < 17; i++) {
                for (int j = 0; j < 12; j++) {
                    if (map_obj[j][i] instanceof DynamicTile) {
                        ((DynamicTile) map_obj[j][i]).update(intervalTime);
                    }
                }
            }
            generator.generateAlien(intervalTime);
        }
    }
    public void openFile(String filename) throws FileNotFoundException {
        tileMap=new int[13][18][2];
        try {
            Scanner input = new Scanner(Paths.get(filename));
            int i = 0;
            int j = 0;
            while (input.hasNext()) { // while there is more to read
                // display each read word and add it to the ArrayList wordList
                String parses = input.next();
                System.out.println(parses);
                String[] block = parses.split(",");
                if (block.length == 1) {
                    tileMap[i][j][0] = Integer.parseInt(block[0]);
                    System.out.println("len is 1 and " + tileMap[i][j][0]);
                    tileMap[i][j][1] = -1;

                } else {
                    tileMap[i][j][0] = Integer.parseInt(block[0]);
                    tileMap[i][j][1] = Integer.parseInt(block[1]);
                    System.out.println("len is 2 and " + tileMap[i][j][0] + " , " + tileMap[i][j][1]);

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
}
