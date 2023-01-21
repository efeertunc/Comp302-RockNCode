package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import domain.controllers.HelpController;
import main.IAppView;
import main.IPanel;

public class HelpPanel implements IPanel {
	
	private JPanel panel;
	
	private HelpController helpController;

	JTextPane textPane;

	private JButton backButton;
	
	public HelpPanel(IAppView appView) {
		putPaneltoFrame(appView.getFrame());
		this.helpController = new HelpController();	
		initialize();
		design();
	}
	
	
	@Override
	public void design() {
		
		JLabel BigLabel = new JLabel("HELP PANEL");
		BigLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 27));
		BigLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		BigLabel.setHorizontalAlignment(SwingConstants.CENTER);
		BigLabel.setBounds(350, 6, 313, 84);
		panel.add(BigLabel);

		backButton.setBounds(20, 18, 117, 29);
		panel.add(backButton);

	}


	@Override
	public void putPaneltoFrame(JFrame frame) {
		panel = new JPanel();
		frame.add(this.panel);	
		panel.setVisible(false);
		panel.setBounds(120, 50, 1038, 582);
		panel.setLayout(null);
		panel.setBorder(new CompoundBorder(new LineBorder(new Color(0, 153, 153), 8, true),
				new MatteBorder(9, 10, 9, 9, (Color) new Color(204, 51, 0))));
	}


	@Override
	public void showPanel(Boolean show) {
		panel.setVisible(show);	
	}


	@Override
	public void initialize() {
		backButton = new JButton("<-");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});

		textPane = new JTextPane();
		textPane.setBackground(new Color(255, 255, 204));
		textPane.setBounds(90, 100, 800, 350);
		textPane.setText(
				"Building mode ->   Click to map to insert obstacles. After that you can continue to other " +
						"buildings and start the running mode." +
						"\n" + "Running mode -> Main objective is to find the key and pass the door. " +
						"Time will be renewed in the each building accordingly number of obstacles. \n"
						+"Powerups -> There are 5 types of powerups that can be clicked and collected during the running mode. First one is " +
						"Vest power up, it guards the player 15 seconds long and avoids player from loosing his/her life. To use this power-up click “V”. Hint power up can be used by clicking " +
						"“H” key, it will immediately show the place of the key. Extra life power up directly adds 1 life to the player lives. Extra time powerup is also directly" +
						" added to the players time. Last one is bottle, avatar holds"
						+"the bottle ones the B key clicked, then gives a direction to throw. \n" +
						"Aliens ->  Shooter alien appears in random locations in the building " +
						" and shoots a bullet every second. Blind alien tries to kill the player, " +
						"it randomly walks. Time wasting alien changes location of the key every 5 seconds. \n"

		);
		panel.add(textPane);
	}


	protected void back() {
		
		helpController.back();
		
	}

}
