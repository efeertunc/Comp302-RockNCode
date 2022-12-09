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
import domain.Avatar;
import domain.BuildingTracker;
import domain.Obstacle;
import main.IPanel;
import objects.TileManager;

public class RunningMap extends JPanel implements IPanel , Runnable {
    JPanel panel;
    TileManager tm;
    Point startPoint;
    Thread thread;
    private int[][] map = BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getMap();

    public RunningMap(JPanel panel) {
        this.panel = panel;
        tm = new TileManager();
        //initialize();
        design();
    }
    @Override
    public void showPanel(Boolean show) {
        this.setVisible(show);

    }

    @Override
    public void initialize() {
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
        this.map = map;
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
                if (map[j][i] == 0)
                {
                    g2D.drawImage(tm.getObjects()[4].image, parseX(i), parseY(j), 48,
                            48, null);
                }
                else
                {
                    g2D.drawImage(tm.getObjects()[map[j][i] -1].image, parseX(i), parseY(j), 48,
                            48, null);
                }
            }
        }
    }
    public void printArr(int[][] arr) {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 17; j++) {
                System.out.printf("%d", arr[i][j]);
            }
            System.out.println();
        }
    }
    public void printAll(){
        System.out.printf("All the information for %s \n",BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getType().toString());
        printArr(map);
    }


    public void startThread()
    {
        thread = new Thread(this);
        thread.start();
    }
    @Override
    public void run() {
        while (thread != null)
        {
            update();

            repaint();
        }
    }

    public void update()
    {

    }

}
