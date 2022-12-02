package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controllers.RunController;
import main.IAppView;
import main.IPanel;


public class RunPanel implements IPanel{
	
	private JPanel panel;
	
	private JButton pauseButton;
	
	private JPanel RunningMap;
	private JPanel playerPanel;
	
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
		
		
		RunningMap = new JPanel();
		RunningMap.setBorder(new LineBorder(new Color(255, 120, 241)));
		RunningMap.setBounds(16, 44, 303, 292);
		panel.add(RunningMap);
		
		JTextField runningInfo = new JTextField();
		runningInfo.setText("Running Mode");
		runningInfo.setHorizontalAlignment(SwingConstants.CENTER);
		runningInfo.setBounds(82, 124, 130, 26);
		RunningMap.add(runningInfo);
		
		
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
		panel.setBounds(6, 6, 438, 342);
		panel.setLayout(null);
		panel.setBorder(new LineBorder(Color.BLACK));
	}


	@Override
	public void showPanel(Boolean show) {
		panel.setVisible(show);
		
	}


	@Override
	public void initialize() {
		pauseButton = new JButton("Pause Game");	
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pauseGame();
			}
		});
		
	
	}


	protected void pauseGame() {
		runController.pause();
	}

}
