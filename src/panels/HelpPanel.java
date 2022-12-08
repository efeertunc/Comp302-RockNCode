package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import controllers.HelpController;
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
				"Escape from KOÇ is an easy-to-play game that combines fun and challenge. The game space takes place"
						+ " on the KOÇ university campus where a student is trying to find a sequence of keys in the campus buildings."
						+ " The game starts when the player enters one of the buildings and starts looking for a key in different rooms."
						+ " During that journey, aliens may show up and try to catch the player, who should try to escape or distract them. "
						+ "The player is aiming at finding the key before the timeout. To accomplish that, some hints show up here and there."
						+ " Once the key is found, the building will be marked as complete and the player can choose the next open building, "
						+ "which is basically the next level. Some promotions can be offered, like adding more time. The game is over If the player "
						+ "fails to find the key within the time limit."
						+ " If he manages to find all the keys, then he wins the game.");
		panel.add(textPane);

	}

	protected void back() {

		helpController.back();

	}

}
