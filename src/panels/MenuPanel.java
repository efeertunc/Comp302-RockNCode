package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import controllers.MenuController;
import main.IAppView;
import main.IPanel;

public class MenuPanel implements IPanel{
	
	private JPanel panel;
	
	private MenuController menuController;
	
	private JButton playGameButton;
	private JButton helpButton;
	private JButton exitButton;
	
	public MenuPanel(IAppView appView) {
		putPaneltoFrame(appView.getFrame());	
		this.menuController = new MenuController();
		initialize();
		design();
		
	}
	

	
	private void play() {
		menuController.startBuildingMode();
		
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
		BigLabel.setBounds(62, 53, 313, 84);
		panel.add(BigLabel);
		
		
		playGameButton.setBounds(160, 149, 117, 29);
		panel.add(playGameButton);
		

		helpButton.setBounds(160, 190, 117, 29);
		panel.add(helpButton);
		

		exitButton.setBounds(160, 232, 117, 29);
		panel.add(exitButton);

	}


	
}
