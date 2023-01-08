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
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import domain.controllers.PauseController;
import factory.PanelType;
import factory.ViewType;
import main.EscapeFromKoc;
import main.IAppView;
import main.IPanel;


public class PausePanel implements IPanel{
	
	private JPanel panel;
	
	private PauseController pauseController;

	private JButton resumeButton;
	private JButton helpButton;
	private JButton returnMenuButton;

	private JButton saveAndExitButton;
	
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

		saveAndExitButton.setBounds(129, 230, 174, 29);
		panel.add(saveAndExitButton);

	}


	@Override
	public void putPaneltoFrame(JFrame frame) {
		panel = new JPanel();	
		panel.setVisible(false);
		panel.setBounds(420, 200, 438, 342);
		panel.setLayout(null);
		panel.setBorder(new CompoundBorder(new LineBorder(new Color(255, 0, 102), 7, true),
				new MatteBorder(4, 4, 2, 2, (Color) new Color(0, 204, 255))));		frame.add(panel);
	}


	@Override
	public void showPanel(Boolean show) {
		panel.setVisible(show);	
	}


	@Override
	public void initialize() {
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

		saveAndExitButton = new JButton("Save & Exit");
		saveAndExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveAndExit();
			}
		});
		
	}

	private void saveAndExit() {
		pauseController.saveAndExit();
	}


	protected void returnMenu() {
		pauseController.openMenu();
	}


	protected void openHelp() {
		pauseController.openHelp();
	}


	protected void resume() {
		((RunPanel)EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Run)).RunningMap.isPaused = false;
		pauseController.resumeGame();

	}

}
