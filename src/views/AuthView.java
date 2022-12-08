package views;

import javax.swing.JFrame;

import factory.PanelFactory;
import factory.PanelType;
import main.IAppView;
import main.IPanel;

public class AuthView implements IAppView {

	private JFrame frame;
	private final IPanel authPanel;

	public AuthView() {
		putFrametoGame();

		authPanel = PanelFactory.getInstance().createPanel(PanelType.Auth, this);
	}

	@Override
	public void putFrametoGame() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setVisible(false);
		frame.setBounds(100, 100, 450, 382);
		frame.setLocationRelativeTo(null);
	}

	@Override
	public void showView(Boolean show) {
		frame.setVisible(show);
	}

	@Override
	public IPanel getPanel(PanelType panel) {
		return switch (panel) {
		case Auth -> authPanel;
		default -> throw new IllegalArgumentException("No such kind of panel type");
		};
	}

	@Override
	public JFrame getFrame() {
		return frame;
	}

	@Override
	public void createrunPanel() {
		// TODO Auto-generated method stub

	}

}
