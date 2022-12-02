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

import controllers.HelpController;
import main.IAppView;
import main.IPanel;

public class HelpPanel implements IPanel {
	
	private JPanel panel;
	
	private HelpController helpController;
	
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
		BigLabel.setBounds(62, 53, 313, 84);
		panel.add(BigLabel);
		
		backButton.setBounds(6, 11, 117, 29);
		panel.add(backButton);

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
		backButton = new JButton("<-");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		
	}


	protected void back() {
		
		helpController.back();
		
	}

}
