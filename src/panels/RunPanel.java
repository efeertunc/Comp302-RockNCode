package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import HelperComponents.Direction;
import controllers.RunController;
import main.IAppView;
import main.IPanel;



public class RunPanel extends JPanel implements IPanel, KeyListener{
	
	private JPanel panel;
	
	private JButton pauseButton;
	
	private JPanel playerPanel;

	private RunningMap RunningMap;
	
	private RunController runController;
	
	public RunPanel(IAppView appView) {
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


		RunningMap = new RunningMap(panel);

		playerPanel = new JPanel();
		playerPanel.setBackground(Color.ORANGE);
		playerPanel.setLayout(null);
		playerPanel.setBorder(new LineBorder(new Color(65, 238, 67)));
		playerPanel.setBounds(910, 70, 380, 630);
		panel.add(playerPanel);
		
		
		playerPanel = new JPanel();
		playerPanel.setBorder(new LineBorder(new Color(65, 238, 67)));
		playerPanel.setBounds(325, 44, 107, 292);
		panel.add(playerPanel);

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


	@Override
	public void showPanel(Boolean show) {
		panel.setVisible(show);
		panel.requestFocus();
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
		RunningMap = new RunningMap(panel);
		RunningMap.startThread();
	}


	protected void pauseGame() {
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
