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

import controllers.BuildController;
import controllers.MenuController;
import domain.Building;
import domain.BuildingType;
import factory.PanelType;
import main.IAppView;
import main.IPanel;

public class BuildPanel implements IPanel {
	
	private JPanel panel;
	
	private JButton helpButton;
	private JButton nextBuildingButton;
	private JButton startRunModeButton;
	
	private JPanel BuildingMap;
	private JPanel objectPanel;
	
	private JTextField buildingInfo;
	
	
	private BuildController buildController;
	
	
	public BuildPanel(IAppView appView) {
		putPaneltoFrame(appView.getFrame());
		
		this.buildController = new BuildController();
		initialize();
		design();		
	}
	
	@Override
	public void design() {
		
		JLabel BigLabel = new JLabel("BUILD PANEL");
		BigLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		BigLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		BigLabel.setHorizontalAlignment(SwingConstants.CENTER);
		BigLabel.setBounds(135, 6, 168, 29);
		panel.add(BigLabel);
		
	
		helpButton.setBounds(6, 6, 117, 29);
		panel.add(helpButton);
		
		nextBuildingButton.setBounds(315, 6, 117, 29);
		panel.add(nextBuildingButton);
		

		startRunModeButton.setBounds(315, 6, 117, 29);
		startRunModeButton.setVisible(false);
		panel.add(startRunModeButton);
		
		
		BuildingMap = new JPanel();
		BuildingMap.setBorder(new LineBorder(new Color(255, 120, 241)));
		BuildingMap.setBounds(16, 44, 303, 292);
		panel.add(BuildingMap);
		
		buildingInfo = new JTextField();
		buildingInfo.setText("OMER");
		buildingInfo.setHorizontalAlignment(SwingConstants.CENTER);
		buildingInfo.setBounds(82, 124, 130, 26);
		BuildingMap.add(buildingInfo);
		
		
		objectPanel = new JPanel();
		objectPanel.setBorder(new LineBorder(new Color(65, 238, 67)));
		objectPanel.setBounds(325, 44, 107, 292);
		panel.add(objectPanel);
	

	}


	@Override
	public void showPanel(Boolean show) {
		panel.setVisible(show);	
	}


	@Override
	public void initialize() {
		helpButton = new JButton("Help");	
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showHelp();
			}
		});
		
		nextBuildingButton = new JButton("Next Building");
		nextBuildingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextBuilding();
			}
		});
		
		startRunModeButton = new JButton("Start Run Mode");
		startRunModeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startRunMode();
			}
		});
		
	}

	protected void startRunMode() {
		buildController.startRun();
		
	}

	protected void nextBuilding() {
		Building next = buildController.nextBuilding();
		
		if (next.getType() == BuildingType.SNA) {
			nextBuildingButton.setVisible(false);
			startRunModeButton.setVisible(true);
		}
		
		updateBuildingMap(next);
		
	}

	private void updateBuildingMap(Building next) {
		buildingInfo.setText(next.getType().toString());
		
	}

	protected void showHelp() {
		buildController.openHelpScreen();
		
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


}
