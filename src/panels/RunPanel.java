package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.Insets;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.LineBorder;

import factory.PanelType;
import factory.ViewType;
import helperComponents.Direction;
import domain.controllers.RunController;
import domain.gameObjects.avatar.Avatar;
import domain.building.BuildingTracker;
import main.EscapeFromKoc;
import main.IAppView;
import main.IPanel;


public class RunPanel extends JPanel implements IPanel, KeyListener{
	JLabel labelTimer;
	Font font1 = new Font("Arial", Font.PLAIN, 15);
	private JPanel panel;
	
	private JButton pauseButton;

	private JPanel playerPanel;

	public RunningMap RunningMap;
	JButton zoom;
	JButton zoomout;
	private RunController runController;
	JFrame frame;
	
	public RunPanel(IAppView appView) {
		System.out.println("RunPanel");
		putPaneltoFrame(appView.getFrame());	
		this.runController = new RunController();
		frame=appView.getFrame();
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
		if (labelTimer == null){
			return;
		}
		int seconds =  (int) BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex()).getTime();
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

				System.out.println("The location presses"+x+" , "+y);
				runController.searchKey(x,y);


				if (e.getButton() == MouseEvent.BUTTON1){
					System.out.println("Left button clicked");
					runController.searchKey(x,y);
				}
				else if (e.getButton() == MouseEvent.BUTTON3) {
					System.out.println("Right button clicked");
					runController.searchPowerUp(x,y);
				}


			}

		});



		playerPanel = new PlayerPanel();

		RunningMap = new RunningMap(panel,this);

		BufferedImage image = null;
		try {
			URL file = getClass().getResource("/visual/zoom.png");
			image = ImageIO.read(file);
		} catch (IOException ioex) {
			System.err.println("load error: " + ioex.getMessage());
		}
		ImageIcon icon = new ImageIcon(image);
		zoom = new JButton(icon);
		zoom.setBounds(700, 10, 60, 60);
		zoom.setContentAreaFilled(false);
		// emptyMapButton.setFocusPainted(false);
		zoom.setBorderPainted(false);
		panel.add(zoom);

		try {
			URL file = getClass().getResource("/visual/zoomout.png");
			image = ImageIO.read(file);
		} catch (IOException ioex) {
			System.err.println("load error: " + ioex.getMessage());
		}
		icon = new ImageIcon(image);
		zoomout = new JButton(icon);
		zoomout.setBounds(800, 10, 60, 60);
		zoomout.setContentAreaFilled(false);
		// emptyMapButton.setFocusPainted(false);
		zoomout.setBorderPainted(false);
		panel.add(zoomout);


		zoom.addActionListener(new ActionListener() {
			// add them to building
			@Override
			public void actionPerformed(ActionEvent e) {

				RunningMap.increasecale();
				arangeScale(RunningMap.getScale());

				repaint();
			}
		});
		zoomout.addActionListener(new ActionListener() {
			// add them to building
			@Override
			public void actionPerformed(ActionEvent e) {
				RunningMap.decreaseScale();
				arangeScale(RunningMap.getScale());
				repaint();
			}
		});

		panel.setBounds(0, 0, 1290, 700);
	}


	protected void pauseGame() {
		RunningMap.isPaused = true;
		runController.pause();
	}

	@Override
	public void keyTyped(KeyEvent keyEvent) {}

	@Override
	public void keyPressed(KeyEvent keyEvent) {
		int keyCode = keyEvent.getKeyCode();

		runController.doAction(keyCode);

	}

	@Override
	public void keyReleased(KeyEvent keyEvent) {
	}

	public RunController getRunController() {
		return runController;
	}
	private void arangeScale(Double scale){
		runController.setScale(scale);
		panel.setBounds(0, 0, (int) (1290 * scale), (int) (700 * scale)); // (12x17 grids)
		frame.add(panel);
		RunningMap.setBounds(0, 70, (int) (900 * scale), (int) (630 * scale));
		zoomout.setBounds((int) (700 * scale+120* scale), 10, 60, 60);
		zoom.setBounds((int) (700 * scale), 10, 60,60);
		playerPanel.setBounds((int) (900 * scale+10), 70, (int) (380 * scale), (int) (630 * scale));
	}


	public RunningMap getRunningMap() {
		return RunningMap;
	}
}
