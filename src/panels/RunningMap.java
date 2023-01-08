package panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.*;
import javax.swing.border.LineBorder;

import main.EscapeFromKoc;
import models.Constants;
import domain.*;
import domain.building.BuildingTracker;
import domain.gameObjects.DynamicTile;
import main.IPanel;
import domain.gameObjects.ObjectTile;

public class RunningMap extends JPanel implements Runnable {

    private ObjectTile[][] map_obj;
    int FPS = 60;
    public boolean isPaused;
    private int originalTileSize = 48; // 48x48 tile
    private double scale = 1;

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
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
        generator = new AlienGenerator();
    }

    private int parseX(int x)
    {
        return (int)((42 + x*48)*scale);
    }
    private int parseY(int y)
    {
        return (int)((27 + y*48)*scale);


    }

    public void design() {
        this.setBackground(Color.PINK);
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

    public void draw(Graphics2D g2D) {

        if (thread.isAlive())
        {
            int x;
            int y;
            int weight;
            int height;
        for (int i = 0 ; i< 17; i ++) {
            for (int j = 0; j < 12; j++) {
                if (map_obj[j][i] == null){
                    System.out.println("i" + i + "j" + j);
                    continue;
                }
                //System.out.println("j: " + j + " i: " + i + " map_obj: " + map_obj[j][i].image);
                int imageId = map_obj[j][i].getImage();
                if (imageId != -1) {
                    x=parseX(i);
                    y=parseY(j);
                    weight=(int) (originalTileSize * scale);
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
                        g2D.drawImage(Constants.ImageConstants.ALIEN, x,y, weight,weight, null);
                    }
                    if (imageId == 8) {
                        g2D.drawImage(Constants.ImageConstants.KEY, x,y, weight,weight,null);
                    }
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
            BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).setTime(intervalTime);
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
}
