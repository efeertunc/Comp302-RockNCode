package main;

import factory.PanelType;

import javax.swing.*;

public interface IAppView {

    void showView(Boolean show);

    IPanel getPanel(PanelType panel);

    JFrame getFrame();

    void putFrametoGame();

    void createrunPanel();
}
