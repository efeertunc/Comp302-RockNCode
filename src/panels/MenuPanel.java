package panels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.*;

import domain.controllers.MenuController;
import main.IAppView;
import main.IPanel;

public class MenuPanel implements IPanel{
	
	private JPanel panel;

	private JLabel background;

	private MenuController menuController;
	
	private JButton playGameButton;
	private JButton helpButton;
	private JButton exitButton;

	private boolean isRunningMode = false;
	
	public MenuPanel(IAppView appView) {
		putPaneltoFrame(appView.getFrame());	
		this.menuController = new MenuController();
		initialize();
		design();
		
	}
	

	
	private void play() {
		if (isRunningMode) {
			menuController.startRunMode();
		} else {
			menuController.startBuildingMode();
		}
	}

	private void help() {
		menuController.openHelp();
		System.out.println("help");
	}
	
	private void exit() {
		// TODO Auto-generated method stub
		
		menuController.exit();
	}
	
	@Override
	public void putPaneltoFrame(JFrame frame) {
		panel = new JPanel();
		frame.add(panel);
		panel.setVisible(false);
		panel.setBounds(470, 120, 400, 330);
		panel.setLayout(null);
		panel.setOpaque(false);

		URL imageURL = getClass().getResource("/visual/backgr.png");
		ImageIcon icon = null;
		if (imageURL != null) {
			icon = new ImageIcon(imageURL);
		}
		background = new JLabel(icon);

		background.setBounds(40, 0, 1200, 710);
		frame.add(background);
		background.setVisible(false);
	}

	@Override
	public void showPanel(Boolean show) {
		panel.setVisible(show);
		background.setVisible(show);
	}
	
	@Override
	public void initialize() {

		playGameButton = new JButton("Play Game");
		playGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				play();
			}
		});
		
		helpButton = new JButton("Help");
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				help();
			}
		});
		
		exitButton = new JButton("Exit Game");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});

	}

	@Override
	public void design() {
		
		JLabel BigLabel = new JLabel("ESCAPE FROM KOC");
		BigLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 27));
		BigLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		BigLabel.setHorizontalAlignment(SwingConstants.CENTER);
		BigLabel.setBounds(55, 40, 313, 84);
		panel.add(BigLabel);

		BigLabel.setVisible(false);
		playGameButton.setBounds(120, 189, 147, 29);
		panel.add(playGameButton);


		helpButton.setBounds(120, 230, 147, 29);
		panel.add(helpButton);


		exitButton.setBounds(120, 272, 147, 29);
		panel.add(exitButton);

	}

	public void setRunningMode(boolean runningMode) {
		isRunningMode = runningMode;
	}
}
