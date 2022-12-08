package main;

import javax.swing.JFrame;

import factory.PanelType;

public interface IAppView {

	void showView(Boolean show);

	IPanel getPanel(PanelType panel);

	JFrame getFrame();

	void putFrametoGame();

	void createrunPanel();
}
