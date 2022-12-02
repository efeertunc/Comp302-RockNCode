package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import tile.TileManager;

public class BuildPanel extends JPanel {
	private final int originalTileSize = 16; // 16x16 tile
	private final int scale = 3;

	private final int tileSize = originalTileSize * scale; // 48x48 tile //her parça

	private final int[][] map = new int[12][17];

	private TileManager tileM = new TileManager(this);

	Point startPoint;
	JLabel label;
	ObjectPanel objPanel;
	Building sc;

	ArrayList<Integer> xlist = new ArrayList<Integer>();
	ArrayList<Integer> ylist = new ArrayList<Integer>();
	ArrayList<Integer> type = new ArrayList<Integer>();

	public BuildPanel(JFrame frame) {
		objPanel = new ObjectPanel(frame);
		this.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		this.setPreferredSize(new Dimension(getTileSize() - 30, getTileSize() - 30));
		this.setBackground(Color.PINK);
		this.setDoubleBuffered(true);
		this.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		// this.addKeyListener(keyHandler);
		this.setFocusable(true);

		this.setBounds(0, 0, 900, 650); // (12x17 grids)
		frame.getContentPane().add(this);
		sc = new Building();
		performed();
		// repaint();

	}

	public int getTileSize() {
		return tileSize;
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		draw(g2D);
	}

	public void draw(Graphics2D g2D) {

		for (int i = 0; i < xlist.size(); i++) {
			g2D.drawImage(tileM.objects[type.get(i)].image, xlist.get(i), ylist.get(i), this.getTileSize() + 20,
					this.getTileSize() + 20, null); // arrange tile weight and height

		}

	}

	private void performed() {

		this.addMouseListener(new MouseAdapter() {// provides empty implementation of all
			// MouseListener`s methods, allowing us to
			// override only those which interests us
			@Override // I override only one method for presentation
			public void mousePressed(MouseEvent e) {
				int x = e.getX() - 30;
				int y = e.getY() - 20;
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
					int b = objPanel.getSelectedIndex();
					if (b == 0) {
						type.add(0);
					} else if (b == 1) {
						type.add(1);
					} else {
						type.add(2);
					}
				}
				updateMap();
				repaint();

			}

		});

		objPanel.btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int size = xlist.size();
				xlist.remove(size - 1);
				ylist.remove(size - 1);
				type.remove(size - 1);
				repaint();

			}
		});
		objPanel.btnNewButton_1_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				xlist.removeAll(xlist);
				ylist.removeAll(ylist);
				type.remove(type);
				repaint();

			}
		});

		objPanel.btnNewButton.addActionListener(new ActionListener() {
// add them to building
			@Override
			public void actionPerformed(ActionEvent e) {
				updateMap();
				for (int i = 0; i < 12; i++) {
					for (int j = 0; j < 17; j++) {
						System.out.printf("%d", map[i][j]);
					}
					System.out.println();
				}

			}
		});

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
			map[ylist.get(i) / 50][xlist.get(i) / 50] = type.get(i) + 1;
		}
		sc.map = map;

	}

}
