package panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import domain.BuildingTracker;
import main.IPanel;
import objects.TileManager;

public class RunningMap extends JPanel implements IPanel {
    JPanel panel;
    ArrayList<Integer> xlist = BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getXlist();
    ArrayList<Integer> ylist = BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getYlist();
    ArrayList<Integer> objtype = BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getObjtype();
    TileManager tm;
    Point startPoint;
    private int[][] map = BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getMap();

    public RunningMap(JPanel panel) {
        printAll();

        this.panel = panel;
        tm = new TileManager();
        design();
        repaint();

    }

    @Override
    public void showPanel(Boolean show) {
        this.setVisible(show);

    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub

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
        // TODO Auto-generated method stub

    }

    public ArrayList<Integer> getXlist() {
        return xlist;
    }

    public ArrayList<Integer> getYlist() {
        return ylist;
    }

    public ArrayList<Integer> getObjtype() {
        return objtype;
    }

    public int[][] getMap() {
        return map;
    }

    public void setXlist(ArrayList<Integer> xlist) {
        this.xlist = xlist;
    }

    public void setYlist(ArrayList<Integer> ylist) {
        this.ylist = ylist;
    }

    public void setObjtype(ArrayList<Integer> objtype) {
        this.objtype = objtype;
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

        for (int i = 0; i < xlist.size(); i++) {
            g2D.drawImage(tm.getObjects()[objtype.get(i)].image, xlist.get(i), ylist.get(i), tm.getTileSize() + 15,
                    tm.getTileSize() + 15, null); // arrange tile weight and height

        }

    }

    public boolean inMap(int x, int y) {
        return map[y / 50][x / 50] != 0;
    }

    public void updateMap() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 17; j++) {
                map[i][j] = 0;
            }
        }
        for (int i = 0; i < xlist.size(); i++) {
            map[ylist.get(i) / 50][xlist.get(i) / 50] = objtype.get(i) + 1;
        }

    }
    public void printArray(ArrayList<Integer> arr) {
        for (int i = 0; i < arr.size(); i++) {
            System.out.printf(" %d ", arr.get(i));
        }
        System.out.println();
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
        printArray(xlist);
        printArray(ylist);
        printArray(objtype);
        printArr(map);

    }
}
