package panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import main.IPanel;
import objects.TileManager;

public class BuildingMap extends JPanel implements IPanel {
    ArrayList<Integer> xlist = new ArrayList<Integer>();
    ArrayList<Integer> ylist = new ArrayList<Integer>();
    ArrayList<Integer> objtype = new ArrayList<Integer>();
    TileManager tm;
    JPanel panel;

    Point startPoint;
    private int[][] map = new int[12][17];

    public BuildingMap(JPanel panel) {
        this.panel = panel;
        tm = new TileManager();
        design();
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

    @Override
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

    public void addToMap(int x, int y, int b) {

        int x_reduced = x % 50;
        int x_new;
        if (x > 800) {
            x_new = 800;
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
            y_new = 550;

        } else {
            if (y_reduced < 25) {
                y_new = y - y_reduced;
            } else {
                y_new = y + (50 - y_reduced);
            }
        }
        if (!inMap(x_new, y_new)) {

            xlist.add(x_new);
            ylist.add(y_new);
            if (b == 0) {
                objtype.add(0);
            } else if (b == 1) {
                objtype.add(1);
            } else if (b == 3) {
                objtype.add(2);
            } else {
                objtype.add(3);
            }
        }
        updateMap();
        repaint();

    }

    public void emptyMap() {

        xlist = new ArrayList<Integer>();
        ylist = new ArrayList<Integer>();
        objtype = new ArrayList<Integer>();
        repaint();

    }

    public void undoLast() {

        int size = xlist.size();
        if (size != 0) {
            xlist.remove(size - 1);
            ylist.remove(size - 1);
            objtype.remove(size - 1);
            repaint();
        } else {
            JOptionPane.showMessageDialog(null, "There is not any object to delete!");
        }

    }

    public void printArray(ArrayList<Integer> arr) {
        for (int i = 0; i < arr.size(); i++) {
            System.out.printf(" %d ", arr.get(i));
        }
        System.out.println();
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

}
