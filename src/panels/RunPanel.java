package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.LineBorder;

import helperComponents.Direction;
import domain.controllers.RunController;
import domain.building.BuildingTracker;
import main.IAppView;
import main.IPanel;


public class RunPanel extends JPanel implements IPanel, KeyListener{
	Timer timer;
	JFrame windowTimer;
	JLabel labelTimer;
	Font font1 = new Font("Arial", Font.PLAIN, 15);
	private JPanel panel;
	
	private JButton pauseButton;
	String showSecond, showMinute;
	DecimalFormat dFormat = new DecimalFormat("00");
	int second, minute;
	private JPanel playerPanel;

	public RunningMap RunningMap;
	JButton zoom;
	JButton zoomout;
	Double scale;
	private RunController runController;

	
	public RunPanel(IAppView appView) {
		System.out.println("RunPanel");
		putPaneltoFrame(appView.getFrame());	
		this.runController = new RunController();

		initialize();
		design();

		
	}

	
	public void design() {
		
		JLabel BigLabel = new JLabel("RUN PANEL");
		BigLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 27));
		BigLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		BigLabel.setHorizontalAlignment(SwingConstants.CENTER);
		BigLabel.setBounds(135, 6, 168, 29);
		panel.add(BigLabel);
		
		pauseButton.setBounds(6, 6, 117, 29);
		panel.add(pauseButton);


		playerPanel = new JPanel();
		playerPanel.setBackground(Color.ORANGE);
		playerPanel.setLayout(null);
		playerPanel.setBorder(new LineBorder(new Color(65, 238, 67)));
		playerPanel.setBounds(910, 70, 380, 630);
		panel.add(playerPanel);

		labelTimer = new JLabel("");
		labelTimer.setBounds(80, 100, 180, 70);
		labelTimer.setHorizontalAlignment(JLabel.CENTER);
		labelTimer.setFont(font1);

		playerPanel.add(labelTimer);
		playerPanel.setVisible(true);

		labelTimer.setText("01:00");
		second = 0;
		minute = 1;


	}



	@Override
	public void putPaneltoFrame(JFrame frame) {
		panel = new JPanel();
		frame.add(this.panel);	
		panel.setVisible(false);
		panel.setBounds(0, 0, 1290, 700);
		panel.setLayout(null);
		panel.setBorder(new LineBorder(Color.BLACK));
	}

	public void countdown(){
		if(labelTimer == null){
			return;
		}
		int seconds =  (int)BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getAvatar().getCurrentTime();
		int min = seconds/60;
		int second = seconds - (min*60);

		labelTimer.setText(Integer.toString(min) + ":" + Integer.toString(second));
	}

	@Override
	public void showPanel(Boolean show) {

		panel.setVisible(show);
		panel.requestFocus();
		if(show){
			runController.getSound().playMusic(4);
		}
		else{
			runController.getSound().stopMusic();
		}
	}


	@Override
	public void initialize() {
		panel.addKeyListener(this);
		panel.setFocusable(true);
		pauseButton = new JButton("Pause Game");	
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pauseGame();

			}
		});

		panel.addMouseListener(new MouseAdapter() {// provides empty implementation of all
			// MouseListener`s methods, allowing us to
			// override only those which interests us
			@Override // I override only one method for presentation
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				runController.searchKey(x,y);
			}

		});

		RunningMap = new RunningMap(panel,this);
		scale= RunningMap.getScale();
		zoom = new JButton("Zoom in");
		zoom.setBounds(700, 10, 120, 23);
		panel.add(zoom);
		zoomout = new JButton("Zoom out");
		zoomout.setBounds(800, 10, 120, 23);
		panel.add(zoomout);
		zoom.addActionListener(new ActionListener() {
			// add them to building
			@Override
			public void actionPerformed(ActionEvent e) {
				scale= RunningMap.getScale()*1.05;
				RunningMap.setScale(scale);
				panel.setBounds(0, 0, (int) (1290 * scale), (int) (700 * scale)); // (12x17 grids)
				RunningMap.setBounds(0, 70, (int) (900 * scale), (int) (630 * scale));
				zoom.setBounds((int) (700 * scale), 10, (int) (120* scale), (int) (23 * scale));
				zoomout.setBounds((int) (700 * scale+120* scale), 10, (int) (120 * scale), (int) (23 * scale));
				playerPanel.setBounds((int) (900 * scale+10), 70, (int) (380 * scale), (int) (630 * scale));

				repaint();
			}
		});
		zoomout.addActionListener(new ActionListener() {
			// add them to building
			@Override
			public void actionPerformed(ActionEvent e) {
				scale= RunningMap.getScale()*0.95;
				RunningMap.setScale(scale);
				panel.setBounds(0, 0, (int) (1290 * scale), (int) (700 * scale)); // (12x17 grids)
				RunningMap.setBounds(0, 70, (int) (900 * scale), (int) (630 * scale));
				zoomout.setBounds((int) (700 * scale+120* scale), 10, (int) (120 * scale), (int) (23 * scale));
				zoom.setBounds((int) (700 * scale), 10, (int) (120* scale), (int) (23 * scale));
				playerPanel.setBounds((int) (900 * scale+10), 70, (int) (380 * scale), (int) (630 * scale));
				repaint();
			}
		});

		panel.setBounds(0, 0, 1290, 700);
		RunningMap.startThread();
	}


	protected void pauseGame() {
		RunningMap.isPaused = true;
		runController.pause();
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

	@Override
	public void keyTyped(KeyEvent keyEvent) {

	}
	@Override
	public void keyPressed(KeyEvent keyEvent) {
		int keyCode = keyEvent.getKeyCode();
		if (keyCode == KeyEvent.VK_UP)
		{
			runController.movePlayer(Direction.fourDir.up);
		}
		if (keyCode == KeyEvent.VK_RIGHT)
		{
			runController.movePlayer(Direction.fourDir.right);
		}
		if (keyCode == KeyEvent.VK_DOWN)
		{
			runController.movePlayer(Direction.fourDir.down);
		}
		if (keyCode == KeyEvent.VK_LEFT)
		{
			runController.movePlayer(Direction.fourDir.left);
		}
	}

	@Override
	public void keyReleased(KeyEvent keyEvent) {

	}

}
