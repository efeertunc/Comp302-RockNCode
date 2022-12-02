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

import controllers.PauseController;
import factory.PanelType;
import main.EscapeFromKoc;
import main.IAppView;
import main.IPanel;


public class PausePanel implements IPanel{
	
	private JPanel panel;
	
	private PauseController pauseController;
	
	private JButton resumeButton;
	private JButton helpButton;
	private JButton returnMenuButton;
	
	public PausePanel(IAppView appView) {
		putPaneltoFrame(appView.getFrame());
		this.pauseController = new PauseController();
		initialize();
		design();
	}

	
	@Override
	public void design() {
		
		JLabel BigLabel = new JLabel("PAUSE");
		BigLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 27));
		BigLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		BigLabel.setHorizontalAlignment(SwingConstants.CENTER);
		BigLabel.setBounds(101, 36, 230, 51);
		panel.add(BigLabel);
		 
		resumeButton.setBounds(129, 109, 174, 29);
		panel.add(resumeButton);
		
		helpButton.setBounds(129, 150, 174, 29);
		panel.add(helpButton);
		
		returnMenuButton.setBounds(129, 191, 174, 29);
		panel.add(returnMenuButton);

	}


	@Override
	public void putPaneltoFrame(JFrame frame) {
		panel = new JPanel();	
		panel.setVisible(false);
		panel.setBounds(6, 6, 438, 342);
		panel.setLayout(null);
		panel.setBorder(new LineBorder(Color.BLACK));
		frame.add(panel);
	}


	@Override
	public void showPanel(Boolean show) {
		panel.setVisible(show);	
	}


	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		resumeButton = new JButton("Resume Game");
		resumeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resume();
			}
		});
		
		
		helpButton = new JButton("Help Screen");
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openHelp();
			}
		});
		
		
		returnMenuButton = new JButton("Return to Main Menu");
		returnMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnMenu();
			}
		});
		
	}


	protected void returnMenu() {
		pauseController.openMenu();
	}


	protected void openHelp() {
		pauseController.openHelp();
	}


	protected void resume() {
		pauseController.resumeGame();
		
	}

}
