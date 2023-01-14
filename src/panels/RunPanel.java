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
import domain.gameObjects.alien.shooter.ShooterAlien;
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
	JLabel labelHint;
	JLabel labelBottle;
	JLabel labelLife;
	JLabel keyLabel;

	JLabel labelVest;
	Font font1 = new Font("Arial", Font.PLAIN, 15);
	private JPanel panel;
	
	private JButton pauseButton;

	private JPanel playerPanel;
	public Avatar avatar;
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
		BigLabel.setBounds(35, 6, 168, 29);
		panel.add(BigLabel);
		



		//playerPanel.setBackground(Color.ORANGE);
		playerPanel.setLayout(null);
		playerPanel.setBorder(new LineBorder(new Color(65, 238, 67)));
		playerPanel.setBounds(910, 70, 380, 630);
		panel.add(playerPanel);

		//life

		labelLife = new JLabel("");
		labelLife.setBounds(27, 27, 61, 16);
		labelLife.setHorizontalAlignment(JLabel.CENTER);
		labelLife.setFont(font1);
		labelLife.setText(Integer.toString(3));
		Icon imgIcon1 = new ImageIcon(this.getClass().getResource("/visual/life.png"));
		JLabel label1 = new JLabel(imgIcon1);
		label1.setBounds(27, 27, 16, 16); // You can use your own values
		playerPanel.add(label1);
		//avatar.getLife())

		Icon icon = new ImageIcon(this.getClass().getResource("/visual/keyFound.gif"));
		keyLabel = new JLabel(icon);
		keyLabel.setBounds(100, 250, 100, 300); // You can use your own values
		keyLabel.setVisible(runController.getAvatar().isHasKey());
		playerPanel.add(keyLabel);

		//timer
		labelTimer = new JLabel("");
		labelTimer.setBounds(228, 27, 61, 16);
		labelTimer.setHorizontalAlignment(JLabel.CENTER);
		labelTimer.setFont(font1);


		//bottle
		labelBottle = new JLabel("");
		labelBottle.setBounds(27, 66, 61, 16);
		labelBottle.setHorizontalAlignment(JLabel.CENTER);
		labelBottle.setFont(font1);
		labelBottle.setText(Integer.toString(3));

		Icon imgIcon2 = new ImageIcon(this.getClass().getResource("/visual/bottle.png"));
		JLabel label2 = new JLabel(imgIcon2);
		label2.setBounds(27, 66, 16, 16); // You can use your own values
		playerPanel.add(label2);

		//vest
		labelVest = new JLabel("");
		labelVest.setBounds(112, 27, 61, 16);
		labelVest.setHorizontalAlignment(JLabel.CENTER);
		labelVest.setFont(font1);
		labelVest.setText(Integer.toString(3));

		Icon imgIcon3 = new ImageIcon(this.getClass().getResource("/visual/vest.png"));
		JLabel label3 = new JLabel(imgIcon3);
		label3.setBounds(112, 27, 16, 16); // You can use your own values
		playerPanel.add(label3);


		//hint
		labelHint = new JLabel("");
		labelHint.setBounds(112, 66, 61, 16);
		labelHint.setHorizontalAlignment(JLabel.CENTER);
		labelHint.setFont(font1);
		labelHint.setText(Integer.toString(3));

		Icon imgIcon4 = new ImageIcon(this.getClass().getResource("/visual/quest.png"));
		JLabel label4 = new JLabel(imgIcon4);
		label4.setBounds(112, 66, 16, 16); // You can use your own values
		playerPanel.add(label4);

		pauseButton.setBounds(200, 10, 117, 29);
		panel.add(pauseButton);


		playerPanel.add(labelHint);
		playerPanel.add(labelVest);
		playerPanel.add(labelLife);
		playerPanel.add(labelTimer);
		playerPanel.add(labelBottle);
		playerPanel.setVisible(true);



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



		panel.addMouseListener(new MouseAdapter() {// provides empty implementation of all
			// MouseListener`s methods, allowing us to
			// override only those which interests us
			@Override // I override only one method for presentation
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();

				System.out.println("The location presses"+x+" , "+y);

				if (e.getButton() == MouseEvent.BUTTON1){
					System.out.println("Left button clicked");
					System.out.println("The location presses"+x+" , "+y);
					runController.searchKey(x,y);
					keyLabel.setVisible(runController.getAvatar().isHasKey());
				}

				else if (e.getButton() == MouseEvent.BUTTON3) {
					System.out.println("Right button clicked");
					System.out.println("The location presses"+x+" , "+y);
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

		try {
			URL file = getClass().getResource("/visual/pause.png");
			image = ImageIO.read(file);
		} catch (IOException ioex) {
			System.err.println("load error: " + ioex.getMessage());
		}
		icon = new ImageIcon(image);
		pauseButton = new JButton(icon);
		pauseButton.setBounds(20, 10, 140, 90);
		pauseButton.setContentAreaFilled(false);
		// emptyMapButton.setFocusPainted(false);
		pauseButton.setBorderPainted(false);
		panel.add(pauseButton);

		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pauseGame();

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
		keyLabel.setVisible(runController.getAvatar().isHasKey());
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
