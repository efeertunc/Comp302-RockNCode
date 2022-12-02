package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BuildController {

	private JFrame frame = new JFrame();
	JPanel objPanel = new JPanel();

	public JFrame getFrame() {
		return frame;
	}

	public JPanel getObjPanel() {
		return objPanel;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public void setObjPanel(JPanel objPanel) {
		this.objPanel = objPanel;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuildController window = new BuildController();
					window.frame.setVisible(true);
					window.frame.setResizable(true);
					// window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BuildController() {

		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		startBuildingMode(frame);

	}

	public void startBuildingMode(JFrame frame) {

		BuildPanel studentCenterPanel = new BuildPanel(frame);

	}

}
