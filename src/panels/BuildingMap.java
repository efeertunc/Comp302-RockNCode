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

import Models.Constants;
import domain.BuildingTracker;
import domain.EmptyTile;
import domain.Obstacle;
import main.IPanel;
import objects.ObjectTile;
import objects.TileManager;

public class BuildingMap extends JPanel implements IPanel {
    ArrayList<Integer> xlist = new ArrayList<Integer>();
    ArrayList<Integer> ylist = new ArrayList<Integer>();
    ArrayList<Integer> objtype = new ArrayList<Integer>();
    TileManager tm;
    JPanel panel;

    Point startPoint;
    private ObjectTile[][] map;

    public BuildingMap(JPanel panel) {
        tm = new TileManager();
        map=initial_map();
        this.panel = panel;

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
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 12; j++) {
                int imageId = map[j][i].image;
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

        public void updateMap ( int x, int y,int type){
            map[y][x] = new Obstacle(type,x,y,tm.objects[type].getImage());
        }

        public boolean addToMap ( int x, int y, int b){

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
            // x, y between 0-800 and 0-550
            System.out.printf("x is %d and y is %d",x_new,y_new);
            //Here, we check whether this location is empty or not, by looking at lists
            // For database, we can check in map later.
           if (!inMap(x_new, y_new)) {
               xlist.add(x_new);
               ylist.add(y_new);
               objtype.add(b);
               updateMap(unparseX(x_new), unparseY(y_new), b);
               repaint();
                return true;
           }
        return false;
        }

        public void emptyMap () {

            xlist = new ArrayList<Integer>();
            ylist = new ArrayList<Integer>();
            objtype = new ArrayList<Integer>();
            map = initial_map();
            repaint();

        }

        public void undoLast () {

            int size = xlist.size();
            if (size != 0) {
                int a = unparseY(ylist.get(size - 1));
                int b = unparseX(xlist.get(size - 1));
                map[a][b] = new EmptyTile(b,a,tm.objects[4].getImage());
                xlist.remove(size - 1);
                ylist.remove(size - 1);
                objtype.remove(size - 1);

                repaint();
            } else {
                JOptionPane.showMessageDialog(null, "There is not any object to delete!");
            }

        }

        public void printArray (ArrayList < Integer > arr) {
            for (int i = 0; i < arr.size(); i++) {
                System.out.printf(" %d ", arr.get(i));
            }
            System.out.println();
        }

        public ArrayList<Integer> getXlist () {
            return xlist;
        }

        public ArrayList<Integer> getYlist () {
            return ylist;
        }

        public ArrayList<Integer> getObjtype () {
            return objtype;
        }

        public ObjectTile[][] getMap () {
            return map;
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
        public void printArr (ObjectTile[][]arr){
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 17; j++) {
                    if (arr[i][j] != null) {
                        System.out.printf("%d ", 1);
                    } else {
                        System.out.printf("%s ", 'n');
                    }
                }
                System.out.println();
            }
            System.out.println();
        }
        public ObjectTile[][] initial_map () {
            ObjectTile[][] map = new ObjectTile[12][17];
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 17; j++) {
                    map[i][j] = new EmptyTile(j,i,tm.objects[4].getImage());
                    //System.out.println();
                }
            }
            return map;
        }
    public boolean inMap(int x, int y) {
            if (map[unparseY(y)][unparseX(x)].getImage()!= tm.objects[4].getImage()){
                return true;
            }

        return false;
    }
    }