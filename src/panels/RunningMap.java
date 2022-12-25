package panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import HelperComponents.Direction;
import HelperComponents.Position;
import Models.Constants;
import domain.*;
import main.IPanel;
import objects.ObjectTile;
import objects.TileManager;

public class RunningMap extends JPanel implements IPanel , Runnable {
    int FPS = 60;
    public boolean isPaused;
    JPanel panel;
    TileManager tm;
    Point startPoint;
    Thread thread;
    AlienGenerator generator;  //TEST PURPOSES
    private ObjectTile[][] map_obj = BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getMap_obj();
    public RunningMap(JPanel panel) {
        this.panel = panel;
        tm = new TileManager();
        initialize();
        design();
    }

    @Override
    public void showPanel(Boolean show) {
        this.setVisible(show);
    }

    @Override
    public void initialize() {
        generator = new AlienGenerator();
    }

    private int parseX(int x)
    {
        return 42 + x*48;
    }
    private int parseY(int y)
    {
        return 27 + y*48;
    }
    @Override
    public void design() {
        this.setBackground(Color.PINK);
        this.setLayout(null);
        this.setBorder(new LineBorder(new Color(255, 120, 241)));
        this.setBounds(0, 70, 900, 630);
        panel.add(this);
    }

    @Override
    public void putPaneltoFrame(JFrame frame) {
        //

    }
    public void setMap(int[][] map) {
       //
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        draw(g2D);
    }

    public void draw(Graphics2D g2D) {
        for (int i = 0 ; i< 17; i ++)
        {
            for (int j = 0 ; j <12; j ++)
            {
                int imageId = map_obj[j][i].image;
                if (imageId != -1) {
                    if (imageId == 0){
                        g2D.drawImage(Constants.ImageConstants.SHELVE, parseX(i), parseY(j), 48 + 5,
                                48 + 5, null);
                    }
                    if (imageId == 1){
                        g2D.drawImage(Constants.ImageConstants.CHAIR, parseX(i), parseY(j), 48 + 5,
                                48 + 5, null);
                    }
                    if (imageId == 2){
                        g2D.drawImage(Constants.ImageConstants.BIN, parseX(i), parseY(j), 48 + 5,
                                48 + 5, null);
                    }
                    if (imageId == 3){
                        g2D.drawImage(Constants.ImageConstants.TABLE, parseX(i), parseY(j), 48 + 5,
                                48 + 5, null);
                    }
                    if (imageId == 4){
                        g2D.drawImage(Constants.ImageConstants.EMPTY, parseX(i), parseY(j), 48 + 5,
                                48 + 5, null);
                    }
                    if (imageId == 5){
                        g2D.drawImage(Constants.ImageConstants.AVATAR, parseX(i), parseY(j), 48 + 5,
                                48 + 5, null);
                    }if (imageId == 6){
                        g2D.drawImage(Constants.ImageConstants.AVATAR_HAPPY, parseX(i), parseY(j), 48 + 5,
                                48 + 5, null);
                    }
                    if (imageId == 7){
                        g2D.drawImage(Constants.ImageConstants.ALIEN, parseX(i), parseY(j), 48 + 5,
                                48 + 5, null);
                    }
                    if (imageId == 8){
                        g2D.drawImage(Constants.ImageConstants.KEY, parseX(i), parseY(j), 48 + 5,
                                48 + 5, null);
                    }
                }
            }
        }
    }
    public void printArr(ObjectTile[][] arr) {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 17; j++) {
                System.out.printf("%d", arr[i][j].ID);
            }
            System.out.println();
        }
    }
    public void printAll(){
        System.out.printf("All the information for %s \n",BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getType().toString());
        printArr(map_obj);
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

    public void update(double intervalTime)
    {
        if(!isPaused) {
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
